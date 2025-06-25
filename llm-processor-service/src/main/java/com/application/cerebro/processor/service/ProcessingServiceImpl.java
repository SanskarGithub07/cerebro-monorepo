package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.dto.TranscriptItem;
import com.application.cerebro.processor.dto.TranscriptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {
    private final OpenAiChatClient openAiChatClient;
    @Override
    public SummaryResponseDto generateSummaryFromTranscript(TranscriptRequestDto transcriptRequestDto){
        String transcript = "";
        for(TranscriptItem transcriptItem : transcriptRequestDto.getTranscript()){
            transcript += transcriptItem.getText() + " ";
        }

        String prompt = "Summarize this transcript: \n" + transcript;

        System.out.println(transcript);

        String response = openAiChatClient.call(prompt);
        SummaryResponseDto summaryResponseDto = SummaryResponseDto.builder()
                .summary(response)
                .videoId(transcriptRequestDto.getVideoId())
                .build();
        return summaryResponseDto;
    }
}
