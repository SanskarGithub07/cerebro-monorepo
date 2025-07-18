package com.application.cerebro.processor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_flashcard_deck")
public class FlashCardDeck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    @Column(nullable = false)
    private String videoId;

    @Column(nullable = false)
    private String userId;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlashCard> flashCardList;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}
