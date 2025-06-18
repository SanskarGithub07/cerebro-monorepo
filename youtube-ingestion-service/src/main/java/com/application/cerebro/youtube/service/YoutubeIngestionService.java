package com.application.cerebro.youtube.service;

import com.application.cerebro.youtube.dto.TranscriptRequestDto;
import com.application.cerebro.youtube.dto.TranscriptResponseDto;

public interface YoutubeIngestionService {
    TranscriptResponseDto fetchTranscriptFromLink(TranscriptRequestDto requestDto);
}
