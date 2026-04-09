package com.apirest.questionmicroservice.service;

import com.apirest.questionmicroservice.dto.request.AnswerRequest;
import com.apirest.questionmicroservice.dto.request.QuestionRequest;
import com.apirest.questionmicroservice.dto.response.CheckAnswerResponse;
import com.apirest.questionmicroservice.dto.response.QuestionResponse;
import com.apirest.questionmicroservice.mapper.QuestionMapper;
import com.apirest.questionmicroservice.model.Question;
import com.apirest.questionmicroservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionResponse> getAllQuestions() {

        List<Question> questions = questionRepository.findAll();

        return questionMapper.toResponseList(questions);
    }

    public void addQuestion(QuestionRequest questionReq) {
        Question question = questionMapper.toEntity(questionReq);

        questionRepository.save(question);

        if (question.getOptions() != null) {
            question.getOptions().forEach(option -> option.setQuestion(question));
        }
    }

    public List<QuestionResponse> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);

        return questionMapper.toResponseList(questions);
    }

    public List<QuestionResponse> getQuestionsByDifficultLevel(String level) {
        List<Question> questions = questionRepository.findByDifficultyLevel(level);

        return questionMapper.toResponseList(questions);
    }

    public List<Integer> findRandomQuestionsByCategory(String category, Integer nQuestions) {
        return questionRepository.findRandomQuestionsByCategory(category, nQuestions);
    }

    public List<QuestionResponse> getQuestionsById(List<Integer> ids) {
        List<Question> questions = questionRepository.findAllById(ids);

        return questionMapper.toResponseList(questions);
    }

    public CheckAnswerResponse checkAnswers(List<AnswerRequest> answers) {
        List<Integer> ids = answers.stream().map(AnswerRequest::questionId)
                .collect(Collectors.toList());

        List<Question> questions = questionRepository.findAllById(ids);

        Map<Integer, String> rightAnswersByQuestionId = questions.stream()
                .collect(Collectors.toMap(Question::getId, Question::getRightAnswer));

        int nRightQuestions = (int) answers.stream()
                .filter(answer -> {
                    String rightAnswer = rightAnswersByQuestionId.get(answer.questionId());
                    return Objects.equals(rightAnswer, answer.option());
                })
                .count();

        return CheckAnswerResponse
                .builder()
                .correctQuestions(questionMapper.toResponseList(questions))
                .numOfRightQuestions(nRightQuestions)
                .build();
    }
}
