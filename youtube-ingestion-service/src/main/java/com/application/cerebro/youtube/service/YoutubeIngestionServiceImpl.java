package com.application.cerebro.youtube.service;

import com.application.cerebro.youtube.TranscriptRepository;
import com.application.cerebro.youtube.client.PythonTranscriptClient;
import com.application.cerebro.youtube.dto.TranscriptItemDto;
import com.application.cerebro.youtube.dto.TranscriptRequestDto;
import com.application.cerebro.youtube.dto.TranscriptResponseDto;
import com.application.cerebro.youtube.entity.Transcript;
import com.application.cerebro.youtube.entity.TranscriptItem;
import com.application.cerebro.youtube.exception.ExternalServiceException;
import com.application.cerebro.youtube.exception.TranscriptNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class YoutubeIngestionServiceImpl implements YoutubeIngestionService {

    private final PythonTranscriptClient pythonTranscriptClient;
    private final TranscriptRepository transcriptRepository;

    @Override
    public TranscriptResponseDto fetchTranscriptFromLink(TranscriptRequestDto transcriptRequestDto){
        log.debug("Calling Python service for videoId: {}", transcriptRequestDto.getVideoId());

        try{
            TranscriptResponseDto transcriptResponseDto = pythonTranscriptClient.getTranscript(transcriptRequestDto);
            log.debug("Transcript received: {} items", transcriptResponseDto.getTranscript().size());

            List<TranscriptItemDto> transcriptItemDtoList = transcriptResponseDto.getTranscript();
            if(transcriptItemDtoList.isEmpty()){
                log.warn("Empty transcript for videoId: {}", transcriptRequestDto.getVideoId());
                throw new TranscriptNotFoundException("Transcript not found for video with id: " + transcriptRequestDto.getVideoId());
            }

            List<TranscriptItem> transcriptItems = new ArrayList<>();
            for (TranscriptItemDto transcriptItemDto : transcriptItemDtoList) {
                TranscriptItem transcriptItem = TranscriptItem.builder()
                        .text(transcriptItemDto.getText())
                        .duration(transcriptItemDto.getDuration())
                        .start(transcriptItemDto.getStart())
                        .build();
                transcriptItems.add(transcriptItem);
            }

            Transcript transcript = Transcript.builder()
                    .videoId(transcriptRequestDto.getVideoId())
                    .build();

            for (TranscriptItem item : transcriptItems) {
                item.setTranscript(transcript);
            }
            transcript.setTranscriptItems(transcriptItems);

            transcriptRepository.save(transcript);

            return transcriptResponseDto;
        }catch(RestClientException ex){
            log.error("Failed to fetch transcript from Python service", ex);
            throw new ExternalServiceException("Failed to fetch transcript from python service.", ex);
        }catch(Exception e){
            log.error("Unexpected error occurred in YoutubeIngestionService", e);
            throw new RuntimeException("Unexpected error occurred while processing the transcript.", e);
        }
    }
}
