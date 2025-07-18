package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.*;
import com.application.cerebro.processor.entity.Question;
import com.application.cerebro.processor.entity.Quiz;
import com.application.cerebro.processor.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;
    @Override
    public List<QuizResponseDto> getQuizzes(String userId) {
        List<Quiz> quizList = quizRepository.findAllByUserId(userId);

        List<QuizResponseDto> quizzes = new ArrayList<>();

        for(Quiz quiz : quizList){

            List<QuestionItem> questionItemList = new ArrayList<>();

            for(Question question : quiz.getQuestionList()){
                QuestionItem questionItem = QuestionItem.builder()
                        .questionId(question.getQuestionId())
                        .question(question.getQuestion())
                        .topic(question.getTopic())
                        .correctAnswer(question.getCorrectAnswer())
                        .difficultyLevel(question.getDifficultyLevel())
                        .optionA(question.getOptionA())
                        .optionB(question.getOptionB())
                        .optionC(question.getOptionC())
                        .optionD(question.getOptionD())
                        .build();

                questionItemList.add(questionItem);
            }

            QuizResponseDto quizResponseDto = QuizResponseDto.builder()
                    .title(quiz.getQuizTitle())
                    .quizId(quiz.getQuizId())
                    .questions(questionItemList)
                    .build();

            quizzes.add(quizResponseDto);
        }

        return quizzes;
    }

    @Override
    public QuizResponseDto getQuizFromVideoId(String userId, String videoId){
        Quiz quiz = quizRepository.findByUserIdAndVideoId(userId, videoId).get();
        List<QuestionItem> questions = new ArrayList<>();

        for(Question question : quiz.getQuestionList()){
            QuestionItem questionItem = QuestionItem.builder()
                    .questionId(question.getQuestionId())
                    .question(question.getQuestion())
                    .topic(question.getTopic())
                    .difficultyLevel(question.getDifficultyLevel())
                    .optionA(question.getOptionA())
                    .optionB(question.getOptionB())
                    .optionC(question.getOptionC())
                    .optionD(question.getOptionD())
                    .correctAnswer(question.getCorrectAnswer())
                    .build();

            questions.add(questionItem);
        }

        QuizResponseDto quizResponseDto = QuizResponseDto.builder()
                .questions(questions)
                .quizId(quiz.getQuizId())
                .title(quiz.getQuizTitle())
                .build();

        return quizResponseDto;
    }

    @Override
    public ScoreResponseDto submitQuizAndCalculateScore(String userId, SubmitRequestDto submitRequestDto){
        Optional<Quiz> optionalQuiz = quizRepository.findByUserIdAndQuizId(userId, submitRequestDto.getQuizId());

        if(optionalQuiz.isEmpty()){
            throw new RuntimeException("Quiz not found");
        }

        Quiz quiz = optionalQuiz.get();

        Map<Integer, String> correctAnswerMap = new HashMap<>();
        for(Question question : quiz.getQuestionList()){
            correctAnswerMap.put(question.getQuestionId(), question.getCorrectAnswer());
        }

        int score = 0;

        for(Answer answer : submitRequestDto.getAnswerList()){
            if(correctAnswerMap.containsKey(answer.getQuestionId())){
                String correct = correctAnswerMap.get(answer.getQuestionId());
                if(correct != null && correct.equalsIgnoreCase(answer.getAnswer().trim())){
                    score++;
                }
            }
            else{
                throw new RuntimeException("Invalid question ID in submission");
            }
        }

        ScoreResponseDto scoreResponseDto = ScoreResponseDto.builder()
                .quizId(submitRequestDto.getQuizId())
                .userId(userId)
                .score(score)
                .timestamp(LocalDateTime.now())
                .totalQuestions(quiz.getQuestionList().size())
                .build();

        return scoreResponseDto;
    }
}
