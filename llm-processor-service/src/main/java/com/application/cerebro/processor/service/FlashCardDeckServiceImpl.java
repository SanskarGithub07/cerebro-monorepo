package com.application.cerebro.processor.service;

import com.application.cerebro.processor.dto.FlashCardItem;
import com.application.cerebro.processor.dto.FlashCardResponseDto;
import com.application.cerebro.processor.entity.FlashCard;
import com.application.cerebro.processor.entity.FlashCardDeck;
import com.application.cerebro.processor.repository.FlashCardDeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashCardDeckServiceImpl implements FlashCardDeckService{
    private final FlashCardDeckRepository flashCardDeckRepository;
    @Override
    public List<FlashCardResponseDto> getFlashCards(String userId){
        List<FlashCardDeck> flashCardDeckList = flashCardDeckRepository.findAllByUserId(userId);
        List<FlashCardResponseDto> flashCards = new ArrayList<>();

        for(FlashCardDeck flashCardDeck : flashCardDeckList){
            List<FlashCardItem> flashCardItemList = new ArrayList<>();

            for(FlashCard flashCard : flashCardDeck.getFlashCardList()){
                FlashCardItem flashCardItem = FlashCardItem.builder()
                        .title(flashCard.getTitle())
                        .content(flashCard.getContent())
                        .build();

                flashCardItemList.add(flashCardItem);
            }

            FlashCardResponseDto flashCardResponseDto = FlashCardResponseDto.builder()
                    .videoId(flashCardDeck.getVideoId())
                    .flashCards(flashCardItemList)
                    .build();

            flashCards.add(flashCardResponseDto);
        }

        return flashCards;
    }

    @Override
    public FlashCardResponseDto getFlashCardDeckFromVideoId(String userId, String videoId) {
        FlashCardDeck flashCardDeck = flashCardDeckRepository.findByUserIdAndVideoId(userId, videoId).get();
        List<FlashCardItem> flashcards = new ArrayList<>();

        for(FlashCard flashCard : flashCardDeck.getFlashCardList()){
            FlashCardItem flashCardItem = FlashCardItem.builder()
                    .content(flashCard.getContent())
                    .title(flashCard.getTitle())
                    .build();

            flashcards.add(flashCardItem);
        }

        FlashCardResponseDto flashCardResponseDto = FlashCardResponseDto.builder()
                .videoId(flashCardDeck.getVideoId())
                .flashCards(flashcards)
                .build();

        return flashCardResponseDto;
    }
}
