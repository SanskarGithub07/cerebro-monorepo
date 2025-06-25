package com.application.cerebro.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptItem{
    private String text;
    private Double start;
    private Double duration;
}

