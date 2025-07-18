package com.application.cerebro.processor.controller;

import com.application.cerebro.processor.dto.QuizResponseDto;
import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/summary")
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("")
    public ResponseEntity<List<SummaryResponseDto>> getSummaries(@AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        List<SummaryResponseDto> summaries = summaryService.getSummaries(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(summaries);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<SummaryResponseDto> getSummaryFromVideoId(@AuthenticationPrincipal Jwt jwt, @PathVariable String videoId){
        String userId = jwt.getSubject();
        SummaryResponseDto summary = summaryService.getSummaryFromVideoId(userId, videoId);
        return ResponseEntity.status(HttpStatus.FOUND).body(summary);
    }
}
