package com.application.cerebro.youtube.service;

import com.application.cerebro.youtube.client.PythonTranscriptClient;
import com.application.cerebro.youtube.dto.TranscriptItem;
import com.application.cerebro.youtube.dto.TranscriptRequestDto;
import com.application.cerebro.youtube.dto.TranscriptResponseDto;
import com.application.cerebro.youtube.exception.ExternalServiceException;
import com.application.cerebro.youtube.exception.TranscriptNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeIngestionServiceImpl implements YoutubeIngestionService {

    private final PythonTranscriptClient pythonTranscriptClient;

    @Override
    public TranscriptResponseDto fetchTranscriptFromLink(TranscriptRequestDto transcriptRequestDto){
        try{
            TranscriptResponseDto transcriptResponseDto = pythonTranscriptClient.getTranscript(transcriptRequestDto);

            List<TranscriptItem> transcriptItemList = transcriptResponseDto.getTranscript();
            if(transcriptItemList.isEmpty()){
                throw new TranscriptNotFoundException("Transcript not found for video with id: " + transcriptRequestDto.getVideoId());
            }
            for(TranscriptItem transcriptItem : transcriptItemList){
                System.out.println(transcriptItem.getText());
            }

            return transcriptResponseDto;
        }catch(RestClientException ex){
            throw new ExternalServiceException("Failed to fetch transcript from python service.", ex);
        }catch(Exception e){
            throw new RuntimeException("Unexpected error occurred while processing the transcript.", e);
        }
    }
}
