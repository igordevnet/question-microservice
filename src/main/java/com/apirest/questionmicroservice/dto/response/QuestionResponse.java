package com.apirest.quizapp.dto.response;

import com.apirest.quizapp.model.Option;

import java.util.List;

public record QuestionResponse(
        Integer id,
        String questionTitle,
        String category,
        String difficultyLevel,
        List<Option> options
) {
}
