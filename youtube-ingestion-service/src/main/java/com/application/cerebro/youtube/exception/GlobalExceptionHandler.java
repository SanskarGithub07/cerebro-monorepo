package com.application.cerebro.youtube.exception;

import com.application.cerebro.youtube.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TranscriptNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTranscriptNotFoundException(TranscriptNotFoundException ex, HttpServletRequest request){
        log.warn("Transcript not found for video at URI {}: {}", request.getRequestURI(), ex.getMessage());

        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .error("Transcript Not Found.")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ErrorResponseDto> handleExternalServiceException(ExternalServiceException ex, HttpServletRequest request){
        log.error("External service failed for URI {}: {}", request.getRequestURI(), ex.getMessage());

        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_GATEWAY.value())
                .path(request.getRequestURI())
                .error("External Service Error.")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleOtherExceptions(Exception ex, HttpServletRequest request){
        log.error("Unexpected error at URI {}: {}", request.getRequestURI(), ex.getMessage(), ex);

        ErrorResponseDto error = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .error("Internal Server Error.")
                .message("An unexpected error occurred")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
