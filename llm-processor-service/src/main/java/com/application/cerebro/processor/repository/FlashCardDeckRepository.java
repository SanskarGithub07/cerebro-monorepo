package com.application.cerebro.processor.repository;

import com.application.cerebro.processor.entity.FlashCardDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlashCardDeckRepository extends JpaRepository<FlashCardDeck, Long> {
    List<FlashCardDeck> findAllByUserId(String userId);

    Optional<FlashCardDeck> findByUserIdAndVideoId(String userId, String videoId);
}
