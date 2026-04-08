package com.apirest.quizapp.service;

import com.apirest.quizapp.model.Option;
import com.apirest.quizapp.repository.OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;

    public void saveOptions(List<Option> options) {
        optionRepository.saveAll(options);
    }

    public List<Option> getOptionsByQuestionId(Integer questionId) {
        return optionRepository.findByQuestionId(questionId);
    }
}
