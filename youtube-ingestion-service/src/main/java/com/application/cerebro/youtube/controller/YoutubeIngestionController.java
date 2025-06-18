package com.application.cerebro.youtube.controller;

import com.application.cerebro.youtube.dto.TranscriptRequestDto;
import com.application.cerebro.youtube.dto.TranscriptResponseDto;
import com.application.cerebro.youtube.service.YoutubeIngestionService;
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
public class YoutubeIngestionController {

    private final YoutubeIngestionService youtubeIngestionService;
    @PostMapping("/get-transcript")
    public ResponseEntity<TranscriptResponseDto> fetchTranscriptFromLink(@RequestBody TranscriptRequestDto transcriptRequestDto){
        TranscriptResponseDto responseBody = youtubeIngestionService.fetchTranscriptFromLink(transcriptRequestDto);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
