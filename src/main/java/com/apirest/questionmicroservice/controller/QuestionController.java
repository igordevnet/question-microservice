package com.apirest.questionmicroservice.controller;

import com.apirest.questionmicroservice.dto.request.AnswerRequest;
import com.apirest.questionmicroservice.dto.request.QuestionRequest;
import com.apirest.questionmicroservice.dto.response.CheckAnswerResponse;
import com.apirest.questionmicroservice.dto.response.QuestionResponse;
import com.apirest.questionmicroservice.model.Answer;
import com.apirest.questionmicroservice.service.QuestionService;
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

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam String category,
            @RequestParam Integer nQuestions
    ) {
        return ResponseEntity.ok(questionService.findRandomQuestionsByCategory(category, nQuestions));
    }

    @GetMapping("/get")
    public ResponseEntity<List<QuestionResponse>> getQuestionsById(
            @RequestParam List<Integer> ids
    ) {
        return  ResponseEntity.ok(questionService.getQuestionsById(ids));
    }

    @PostMapping("/check")
    public ResponseEntity<CheckAnswerResponse> checkAnswers(
            @RequestBody List<AnswerRequest> answers
    ) {
        return ResponseEntity.ok(questionService.checkAnswers(answers));
    }
}
