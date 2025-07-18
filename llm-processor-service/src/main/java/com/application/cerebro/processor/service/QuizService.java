package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.QuizResponseDto;
import com.application.cerebro.processor.dto.ScoreResponseDto;
import com.application.cerebro.processor.dto.SubmitRequestDto;

import java.util.List;

public interface QuizService {
    List<QuizResponseDto> getQuizzes(String userId);

    QuizResponseDto getQuizFromVideoId(String userId, String videoId);

    ScoreResponseDto submitQuizAndCalculateScore(String userId, SubmitRequestDto submitRequestDto);
}
