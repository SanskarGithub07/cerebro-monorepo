package com.application.cerebro.youtube.client;

import com.application.cerebro.youtube.dto.TranscriptRequestDto;
import com.application.cerebro.youtube.dto.TranscriptResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface PythonTranscriptClient {
    @PostExchange("/api/get-transcript")
    TranscriptResponseDto getTranscript(@RequestBody TranscriptRequestDto transcriptRequestDto);

}
