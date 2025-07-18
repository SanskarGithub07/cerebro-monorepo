package com.application.cerebro.processor.controller;

import com.application.cerebro.processor.dto.FlashCardResponseDto;
import com.application.cerebro.processor.service.FlashCardDeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flashcard")
@RequiredArgsConstructor
public class FlashCardController {

    private final FlashCardDeckService flashCardDeckService;

    @GetMapping("")
    public ResponseEntity<List<FlashCardResponseDto>> getFlashCards(@AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        List<FlashCardResponseDto> flashCards = flashCardDeckService.getFlashCards(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(flashCards);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<FlashCardResponseDto> getFlashCardDeckFromVideoId(@AuthenticationPrincipal Jwt jwt, @PathVariable String videoId){
        String userId = jwt.getSubject();
        FlashCardResponseDto flashCardDeck = flashCardDeckService.getFlashCardDeckFromVideoId(userId, videoId);
        return ResponseEntity.status(HttpStatus.FOUND).body(flashCardDeck);
    }
}
