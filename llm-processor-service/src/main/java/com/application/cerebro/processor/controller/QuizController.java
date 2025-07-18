package com.application.cerebro.processor.controller;

import com.application.cerebro.processor.dto.QuizResponseDto;
import com.application.cerebro.processor.dto.ScoreResponseDto;
import com.application.cerebro.processor.dto.SubmitRequestDto;
import com.application.cerebro.processor.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("")
    public ResponseEntity<List<QuizResponseDto>> getQuizzes(@AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        List<QuizResponseDto> quizzes = quizService.getQuizzes(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(quizzes);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<QuizResponseDto> getQuizFromVideoId(@AuthenticationPrincipal Jwt jwt, @PathVariable String videoId){
        String userId = jwt.getSubject();
        QuizResponseDto quiz = quizService.getQuizFromVideoId(userId, videoId);
        return ResponseEntity.status(HttpStatus.FOUND).body(quiz);
    }

    @PostMapping("/submit")
    public ResponseEntity<ScoreResponseDto> submitQuizAndCalculateScore(@AuthenticationPrincipal Jwt jwt, @RequestBody SubmitRequestDto submitRequestDto){
        String userId = jwt.getSubject();
        ScoreResponseDto score = quizService.submitQuizAndCalculateScore(userId, submitRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(score);
    }
}
