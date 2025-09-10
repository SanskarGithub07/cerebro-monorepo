package com.application.cerebro.youtube;

import com.application.cerebro.youtube.entity.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Long>{
}
