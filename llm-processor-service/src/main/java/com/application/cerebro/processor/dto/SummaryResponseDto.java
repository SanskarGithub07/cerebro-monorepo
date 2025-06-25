package com.application.cerebro.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SummaryResponseDto {
    private String summary;
    private String videoId;
}
