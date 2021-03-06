package edu.northeastern.cs4550.repositories.widget.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.northeastern.cs4550.models.widget.exam.ExamQuestion;

@Repository
public interface BaseExamQuestionRepository<T extends ExamQuestion> extends JpaRepository<T,Integer> {

    List<T> findByExamId(int examId);
}
