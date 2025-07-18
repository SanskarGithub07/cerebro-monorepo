package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.SummaryResponseDto;
import com.application.cerebro.processor.entity.Summary;
import com.application.cerebro.processor.repository.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final SummaryRepository summaryRepository;

    @Override
    public List<SummaryResponseDto> getSummaries(String userId){
        List<Summary> summaries = summaryRepository.findAllByUserId(userId);
        List<SummaryResponseDto> summaryResponseDtoList = new ArrayList<>();

        for(Summary summary : summaries){
            SummaryResponseDto summaryResponseDto = SummaryResponseDto.builder()
                    .summary(summary.getSummary())
                    .videoId(summary.getVideoId())
                    .build();

            summaryResponseDtoList.add(summaryResponseDto);
        }

        return summaryResponseDtoList;
    }

    @Override
    public SummaryResponseDto getSummaryFromVideoId(String userId, String videoId) {
        Summary summary = summaryRepository.findByUserIdAndVideoId(userId, videoId).get();

        SummaryResponseDto summaryResponseDto = SummaryResponseDto.builder()
                .videoId(summary.getVideoId())
                .summary(summary.getSummary())
                .build();

        return summaryResponseDto;
    }
}
