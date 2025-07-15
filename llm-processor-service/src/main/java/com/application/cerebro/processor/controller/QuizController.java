package com.application.cerebro.processor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/get")
public class QuizController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World from LLM Processor Service";
    }
}
