package com.application.cerebro.processor.controller;

import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/get")
public class SummaryController {

    private final SummaryService summaryService;

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World from LLM Processor Service";
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<SummaryResponseDto>> getSummaries(@AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        List<SummaryResponseDto> summaryResponseDtoList = summaryService.getSummaries(userId);
        return ResponseEntity.status(HttpStatus.OK).body(summaryResponseDtoList);
    }
}
