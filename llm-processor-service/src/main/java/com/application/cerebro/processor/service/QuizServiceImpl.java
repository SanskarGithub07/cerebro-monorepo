package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.QuestionItem;
import com.application.cerebro.processor.dto.QuizResponseDto;
import com.application.cerebro.processor.entity.Question;
import com.application.cerebro.processor.entity.Quiz;
import com.application.cerebro.processor.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .title(quiz.getQuizTitle())
                .build();

        return quizResponseDto;
    }
}
