package com.application.cerebro.processor.repository;

import com.application.cerebro.processor.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByUserId(String userId);

    Optional<Quiz> findByUserIdAndVideoId(String userId, String videoId);
}
