package com.apirest.quizapp.controller;

import com.apirest.quizapp.dto.request.QuestionRequest;
import com.apirest.quizapp.dto.response.QuestionResponse;
import com.apirest.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByCategory(
            @PathVariable() String category
    ) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByDifficultyLevel(
            @PathVariable() String level
    ) {
        return ResponseEntity.ok(questionService.getQuestionsByDifficultLevel(level));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(
            @RequestBody() QuestionRequest question
    ) {
        questionService.addQuestion(question);

        return new ResponseEntity<>("New question created successfully!", HttpStatus.CREATED);
    }
}
