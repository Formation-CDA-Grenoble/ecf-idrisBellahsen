package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Category;
import com.example.api.repository.CategoryRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/categorys")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("")
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable(value = "id") long id) {
        return categoryRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category does not exist")
        );
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody Category Category) {
        return categoryRepository.save(Category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(
        @Valid @RequestBody Category newCategory,
        @PathVariable(value = "id") long id
    ) {
        Category Category = categoryRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category does not exist")
        );
        if (newCategory.getId() != 0 && newCategory.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category id does not match requested resource id");
        }
        Category.setName(newCategory.getName());
        return categoryRepository.save(Category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "id") long id) {
        categoryRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category does not exist")
        );
        categoryRepository.deleteById(id);
    }
}
