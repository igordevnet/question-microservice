package com.apirest.questionmicroservice.dto.request;

import com.apirest.questionmicroservice.model.Option;

import java.util.List;

public record QuestionRequest(
        Integer id,
        String questionTitle,
        String category,
        String rightAnswer,
        String difficultyLevel,
        List<Option> options
) { }
