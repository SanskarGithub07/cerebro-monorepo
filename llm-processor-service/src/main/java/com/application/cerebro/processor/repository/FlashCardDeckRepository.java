package com.application.cerebro.processor.repository;

import com.application.cerebro.processor.entity.FlashCardDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardDeckRepository extends JpaRepository<FlashCardDeck, Long> {
}
