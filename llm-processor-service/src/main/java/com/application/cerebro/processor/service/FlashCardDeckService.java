package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.FlashCardResponseDto;

import java.util.List;

public interface FlashCardDeckService {
    List<FlashCardResponseDto> getFlashCards(String userId);

    FlashCardResponseDto getFlashCardDeckFromVideoId(String userId, String videoId);
}
