package com.application.cerebro.youtube.dto;

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
