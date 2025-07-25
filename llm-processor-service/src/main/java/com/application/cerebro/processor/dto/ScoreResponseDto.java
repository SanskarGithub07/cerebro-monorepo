package com.application.cerebro.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreResponseDto {
    private Long quizId;
    private String userId;
    private Integer score;
    private Integer totalQuestions;
    private LocalDateTime timestamp;

}
