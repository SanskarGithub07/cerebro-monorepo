package com.application.cerebro.processor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_summaries")
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long summaryId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String videoId;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;
}
