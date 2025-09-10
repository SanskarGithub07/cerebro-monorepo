package com.application.cerebro.youtube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transcripts")
public class Transcript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transcriptId;

    @Column(nullable = false)
    private String videoId;

    @OneToMany(mappedBy = "transcript", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TranscriptItem> transcriptItems;
}
