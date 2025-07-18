package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.QuizResponseDto;

import java.util.List;

public interface QuizService {
    List<QuizResponseDto> getQuizzes(String userId);

    QuizResponseDto getQuizFromVideoId(String userId, String videoId);
}
