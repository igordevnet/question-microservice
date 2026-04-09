package com.apirest.questionmicroservice.dto.response;

import com.apirest.questionmicroservice.model.Option;

import java.util.List;

public record QuestionResponse(
        Integer id,
        String questionTitle,
        String category,
        String difficultyLevel,
        List<Option> options
) {
}
