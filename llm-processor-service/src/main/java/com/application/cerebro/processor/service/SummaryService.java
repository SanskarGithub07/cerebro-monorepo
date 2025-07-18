package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.SummaryResponseDto;

import java.util.List;

public interface SummaryService {
    List<SummaryResponseDto> getSummaries(String userId);

    SummaryResponseDto getSummaryFromVideoId(String userId, String videoId);
}
