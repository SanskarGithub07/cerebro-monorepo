package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.*;
import com.application.cerebro.processor.entity.*;
import com.application.cerebro.processor.repository.FlashCardDeckRepository;
import com.application.cerebro.processor.repository.QuizRepository;
import com.application.cerebro.processor.repository.SummaryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService {
    private final OpenAiChatClient openAiChatClient;
    private final SummaryRepository summaryRepository;
    private final QuizRepository quizRepository;
    private final FlashCardDeckRepository flashCardDeckRepository;

    public String extractTranscriptFromJson(TranscriptRequestDto transcriptRequestDto){
        String transcript = "";
        for(TranscriptItem transcriptItem : transcriptRequestDto.getTranscript()){
            transcript += transcriptItem.getText() + " ";
        }

        return transcript;
    }
    @Override
    public SummaryResponseDto generateSummaryFromTranscript(TranscriptRequestDto transcriptRequestDto){
        String transcript = extractTranscriptFromJson(transcriptRequestDto);

        String prompt = """
            Answer the user query by providing 10-15 detailed key points, each with a concise heading and a simple explanation.
            
            Remember to:
            1. Use simple language suitable for a 5-year-old
            2. Cover the main aspects of the query
            3. Provide clear and concise headings
            4. Explain each heading in a short paragraph
            5. Ensure the explanations are easy to understand
            6. Explain with an example
            
            User Query:
            """ + transcript;

        System.out.println(transcript);

        String response = openAiChatClient.call(prompt);
        SummaryResponseDto summaryResponseDto = SummaryResponseDto.builder()
                .summary(response)
                .videoId(transcriptRequestDto.getVideoId())
                .build();

        Summary summary = Summary.builder()
                .summary(summaryResponseDto.getSummary())
                .videoId(summaryResponseDto.getVideoId())
                .build();

        summaryRepository.save(summary);

        return summaryResponseDto;
    }

    @Override
    public FlashCardResponseDto generateFlashCardsFromTranscript(TranscriptRequestDto transcriptRequestDto) throws JsonProcessingException {
        String transcript = extractTranscriptFromJson(transcriptRequestDto);

        String prompt = String.format("""
                You are given a transcript of a YouTube lecture.
                                
                From this transcript, generate **10 flashcards**, each with:
                                
                1. A concise title or heading that represents a concept or topic from the transcript
                2. A short and clear explanation of that concept (in simple terms)
                                
                Make sure the explanations are:
                - Easy to understand (suitable for a beginner)
                - No longer than 4-5 sentences
                - Self-contained and directly relevant to the title
                                
                Transcript:
                %s
                                
                Return the output in JSON format with fields: "title" and "content".
                                
                """, transcript);

        String response = openAiChatClient.call(prompt);
        System.out.println(response);

        int start = response.indexOf("[");
        int end = response.lastIndexOf("]") + 1;

        if (start != -1 && end != -1 && end > start) {
            String json = response.substring(start, end);

            ObjectMapper objectMapper = new ObjectMapper();
            List<FlashCardItem> flashCardItemList = objectMapper.readValue(json, new TypeReference<List<FlashCardItem>>() {});

            FlashCardResponseDto flashCardResponseDto = FlashCardResponseDto.builder()
                    .flashCards(flashCardItemList)
                    .videoId(transcriptRequestDto.getVideoId())
                    .build();

            FlashCardDeck flashCardDeck = FlashCardDeck.builder()
                    .videoId(transcriptRequestDto.getVideoId())
                    .build();

            List<FlashCard> flashCards = flashCardItemList.stream()
                    .map(cardItem -> FlashCard.builder()
                            .deck(flashCardDeck)
                            .title(cardItem.getTitle())
                            .content(cardItem.getContent())
                            .build()).toList();

            flashCardDeck.setFlashCardList(flashCards);
            flashCardDeckRepository.save(flashCardDeck);

            return flashCardResponseDto;
        } else {
            throw new RuntimeException("Could not extract valid JSON array from LLM response.");
        }
    }

    @Override
    public QuizResponseDto generateQuizFromTrancript(TranscriptRequestDto transcriptRequestDto) throws JsonProcessingException {
        String transcript = extractTranscriptFromJson(transcriptRequestDto);

        String prompt = """
                You are given a transcript of a YouTube lecture.
                        
                Generate some easy questions which have direct answers from the transcript.
                Generate some medium questions which are more challenging than the easy ones.
                Generate some hard questions which require deep conceptual thinking and are difficult to answer.
                Ensure each question has only one correct answer and directly relates to the material covered.
                You have to generate a total of **10 questions**
                        
                Each question should include:
                1. The question text
                2. Four options
                3. The correct answer
                4. A difficulty level (easy or hard)
                5. A topic label
                        
                All of these questions are for a quiz related to the transcript.
                I want the output to be a json response with the following format:
                        
                title: should be the title of the quiz
                questions: [
                    question: Should be the question statement,
                    optionA: first option,
                    optionB: second option,
                    optionC: third option,
                    optionD: fourth option,
                    
                    correctAnswer: correct answer to the problem,
                    difficultyLevel: anything in this set (EASY, MEDIUM, HARD),
                    topic: topic related to the question
                ]
                        
                Transcript:
                """ + transcript;

        String response = openAiChatClient.call(prompt);
        System.out.println(response);

        int start = response.indexOf("{");
        int end = response.lastIndexOf("}") + 1;

        if (start != -1 && end != -1 && start < end) {
            String json = response.substring(start, end);

            ObjectMapper objectMapper = new ObjectMapper();
            QuizResponseDto quizResponseDto = objectMapper.readValue(json, QuizResponseDto.class);

            Quiz quiz = Quiz.builder()
                    .videoId(transcriptRequestDto.getVideoId())
                    .quizTitle(quizResponseDto.getTitle())
                    .build();

            List<Question> questions = quizResponseDto.getQuestions().stream()
                    .map(questionItem -> Question.builder()
                            .quiz(quiz)
                            .question(questionItem.getQuestion())
                            .topic(questionItem.getTopic())
                            .optionA(questionItem.getOptionA())
                            .optionB(questionItem.getOptionB())
                            .optionC(questionItem.getOptionC())
                            .optionD(questionItem.getOptionD())
                            .correctAnswer(questionItem.getCorrectAnswer())
                            .difficultyLevel(questionItem.getDifficultyLevel())
                            .build()).toList();

            quiz.setQuestionList(questions);
            quizRepository.save(quiz);

            return quizResponseDto;
        } else {
            throw new RuntimeException("Failed to extract quiz JSON from response");
        }
    }
}
