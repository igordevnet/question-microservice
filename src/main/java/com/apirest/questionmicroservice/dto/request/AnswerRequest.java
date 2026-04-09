package com.apirest.questionmicroservice.dto.request;

public record AnswerRequest(
        Integer questionId,
        String option
) {
}
