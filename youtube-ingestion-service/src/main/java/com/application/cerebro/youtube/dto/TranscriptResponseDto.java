package com.application.cerebro.youtube.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptResponseDto {
    private String videoId;
    private List<TranscriptItem> transcript;
}
