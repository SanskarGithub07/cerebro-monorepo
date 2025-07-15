package com.application.cerebro.processor.controller;

import com.application.cerebro.processor.dto.FlashCardResponseDto;
import com.application.cerebro.processor.dto.QuizResponseDto;
import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.dto.TranscriptRequestDto;
import com.application.cerebro.processor.service.GenerationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/generate")
@RequiredArgsConstructor
public class GenerationController {

    private final GenerationService generationService;
    @PostMapping("/summary")
    public ResponseEntity<SummaryResponseDto> generateSummaryFromTranscript(@RequestBody @Valid TranscriptRequestDto transcriptRequestDto, @AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        SummaryResponseDto summaryResponseDto = generationService.generateSummaryFromTranscript(transcriptRequestDto, userId);
        return ResponseEntity.status(HttpStatus.OK).body(summaryResponseDto);
    }

    @PostMapping("/flashcards")
    public ResponseEntity<FlashCardResponseDto> generateFlashCardsFromTranscript(@RequestBody @Valid TranscriptRequestDto transcriptRequestDto) throws JsonProcessingException {
        FlashCardResponseDto flashCardResponseDto = generationService.generateFlashCardsFromTranscript(transcriptRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(flashCardResponseDto);
    }

    @PostMapping("/quiz")
    public ResponseEntity<QuizResponseDto> generateQuizFromTranscript(@RequestBody @Valid TranscriptRequestDto transcriptRequestDto) throws JsonProcessingException{
        QuizResponseDto quizResponseDto = generationService.generateQuizFromTrancript(transcriptRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(quizResponseDto);
    }
}
