package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Chapter;
import com.example.api.repository.ChapterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping("")
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Chapter getChapter(@PathVariable(value = "id") long id) {
        return chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter does not exist")
        );
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Chapter createChapter(@Valid @RequestBody Chapter Chapter) {
        return chapterRepository.save(Chapter);
    }

    @PutMapping("/{id}")
    public Chapter updateChapter(
        @Valid @RequestBody Chapter newChapter,
        @PathVariable(value = "id") long id
    ) {
        Chapter Chapter = chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter does not exist")
        );
        if (newChapter.getId() != 0 && newChapter.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chapter id does not match requested resource id");
        }
        Chapter.setName(newChapter.getName());
        Chapter.setContent(newChapter.getContent());
        return chapterRepository.save(Chapter);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteChapter(@PathVariable(value = "id") long id) {
        chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter does not exist")
        );
        chapterRepository.deleteById(id);
    }
}
