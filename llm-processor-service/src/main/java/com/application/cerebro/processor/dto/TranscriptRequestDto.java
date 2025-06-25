package com.application.cerebro.processor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TranscriptRequestDto {

    @NotBlank
    private String videoId;
    private List<TranscriptItem> transcript;
}
