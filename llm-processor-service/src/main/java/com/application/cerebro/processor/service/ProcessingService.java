package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.dto.TranscriptRequestDto;

public interface ProcessingService {

    SummaryResponseDto generateSummaryFromTranscript(TranscriptRequestDto transcriptRequestDto);
}

