package com.apirest.questionmicroservice.mapper;

import com.apirest.questionmicroservice.dto.request.QuestionRequest;
import com.apirest.questionmicroservice.dto.response.QuestionResponse;
import com.apirest.questionmicroservice.model.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface
QuestionMapper {

    QuestionResponse toResponse(Question question);

    List<QuestionResponse> toResponseList(List<Question> questions);

    Question toEntity(QuestionRequest request);
}