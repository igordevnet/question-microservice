package com.apirest.quizapp.mapper;

import com.apirest.quizapp.dto.request.QuestionRequest;
import com.apirest.quizapp.dto.response.QuestionResponse;
import com.apirest.quizapp.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface
QuestionMapper {

    QuestionResponse toResponse(Question question);

    List<QuestionResponse> toResponseList(List<Question> questions);

    Question toEntity(QuestionRequest request);
}