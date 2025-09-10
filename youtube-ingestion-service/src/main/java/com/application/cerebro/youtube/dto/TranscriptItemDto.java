package com.application.cerebro.youtube.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptItemDto {
    private String text;
    private Double start;
    private Double duration;
}
