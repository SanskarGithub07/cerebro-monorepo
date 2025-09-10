package com.application.cerebro.youtube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transcript_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranscriptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Double start;
    private Double duration;

    @ManyToOne
    @JoinColumn(name = "transcript_id", nullable = false)
    private Transcript transcript;
}

