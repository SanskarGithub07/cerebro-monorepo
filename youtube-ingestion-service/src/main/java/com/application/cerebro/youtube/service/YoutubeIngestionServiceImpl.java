package com.application.cerebro.youtube.service;

import com.application.cerebro.youtube.client.PythonTranscriptClient;
import com.application.cerebro.youtube.dto.TranscriptItem;
import com.application.cerebro.youtube.dto.TranscriptRequestDto;
import com.application.cerebro.youtube.dto.TranscriptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeIngestionServiceImpl implements YoutubeIngestionService {

    private final PythonTranscriptClient pythonTranscriptClient;

    @Override
    public TranscriptResponseDto fetchTranscriptFromLink(TranscriptRequestDto transcriptRequestDto){
        TranscriptResponseDto transcriptResponseDto = pythonTranscriptClient.getTranscript(transcriptRequestDto);

        List<TranscriptItem> transcriptItemList = transcriptResponseDto.getTranscript();
        for(TranscriptItem transcriptItem : transcriptItemList){
            System.out.println(transcriptItem.getText());
        }

        return transcriptResponseDto;
    }
}
