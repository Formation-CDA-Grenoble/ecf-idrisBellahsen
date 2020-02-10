package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Book;
import com.example.api.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable(value = "id") long id) {
        return bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book does not exist")
        );
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book Book) {
        return bookRepository.save(Book);
    }

    @PutMapping("/{id}")
    public Book updateBook(
        @Valid @RequestBody Book newBook,
        @PathVariable(value = "id") long id
    ) {
        Book Book = bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book does not exist")
        );
        if (newBook.getId() != 0 && newBook.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book id does not match requested resource id");
        }
        Book.setTitle(newBook.getTitle());
        Book.setPicture(newBook.getPicture());
        return bookRepository.save(Book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(value = "id") long id) {
        bookRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book does not exist")
        );
        bookRepository.deleteById(id);
    }
}
