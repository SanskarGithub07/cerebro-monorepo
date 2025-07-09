package com.application.cerebro.processor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @Column(nullable = false)
    private String videoId;

    private LocalDateTime createdAt;

    private String quizTitle;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questionList;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }
}

// orphanRemoval = true ensures that questions get deleted when removed from the quiz
// cascade type of ALL ensures that the questions get saved when the quiz is saved
// mappedBy = "quiz" comes from the private Quiz quiz declaration in the Question entity
