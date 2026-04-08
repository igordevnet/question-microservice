package com.apirest.quizapp.repository;

import com.apirest.quizapp.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

    List<Option> findByQuestionId(Integer questionId);
}
