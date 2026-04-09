package com.apirest.questionmicroservice.repository;

import com.apirest.questionmicroservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByDifficultyLevel(String level);
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :nQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer nQuestions);
}
