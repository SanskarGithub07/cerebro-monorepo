package com.application.cerebro.processor.repository;

import com.application.cerebro.processor.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {
    List<Summary> findAllByUserId(String userId);

    Optional<Summary> findByUserIdAndVideoId(String userId, String videoId);
}
