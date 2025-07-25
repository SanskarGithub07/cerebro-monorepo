package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.FlashCardResponseDto;
import com.application.cerebro.processor.dto.QuizResponseDto;
import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.dto.TranscriptRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface GenerationService {

    SummaryResponseDto generateSummaryFromTranscript(TranscriptRequestDto transcriptRequestDto, String userId);

    FlashCardResponseDto generateFlashCardsFromTranscript(TranscriptRequestDto transcriptRequestDto, String userId) throws JsonProcessingException;

    QuizResponseDto generateQuizFromTrancript(TranscriptRequestDto transcriptRequestDto, String userId) throws JsonProcessingException;
}

