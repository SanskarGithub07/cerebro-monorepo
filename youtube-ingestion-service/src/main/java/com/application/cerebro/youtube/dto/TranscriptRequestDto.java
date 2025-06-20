package com.application.cerebro.youtube.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptRequestDto {
    @NotBlank
    private String videoId;
}
