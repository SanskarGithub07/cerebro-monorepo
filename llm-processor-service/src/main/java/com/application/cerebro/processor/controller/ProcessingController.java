package com.application.cerebro.processor.controller;

import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.dto.TranscriptRequestDto;
import com.application.cerebro.processor.service.ProcessingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessingController {

    private final ProcessingService processingService;
    @PostMapping("/summarize")
    public ResponseEntity<SummaryResponseDto> generateSummaryFromTranscript(@RequestBody @Valid TranscriptRequestDto transcriptRequestDto){
        SummaryResponseDto summaryResponseDto = processingService.generateSummaryFromTranscript(transcriptRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(summaryResponseDto);
    }
}
