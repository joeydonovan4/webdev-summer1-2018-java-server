package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import edu.northeastern.cs4550.models.Lesson;
import edu.northeastern.cs4550.services.ILessonService;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private ILessonService lessonService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable(value = "id") int id) {
        Lesson deletedLesson = lessonService.deleteLesson(id);
        return ResponseEntity.ok(deletedLesson);
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> findAllLessons() {
        List<Lesson> allLessons = lessonService.findAllLessons();
        return ResponseEntity.ok(allLessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> findLessonById(@PathVariable(value = "id") int id) {
        Lesson lesson = lessonService.findLessonById(id);
        return ResponseEntity.ok(lesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLesson(@PathVariable(value = "id") int id,
                                       @Valid @RequestBody Lesson lesson) {
        if (id != lesson.getId()) {
            return ResponseEntity.badRequest().body("ID in path does not match ID in Lesson object");
        }
        Lesson updatedLesson = lessonService.updateLesson(lesson);
        return ResponseEntity.ok(updatedLesson);
    }
}
