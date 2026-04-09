package com.apirest.questionmicroservice.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CheckAnswerResponse(
        Integer numOfRightQuestions,
        List<QuestionResponse> correctQuestions
) {
}
