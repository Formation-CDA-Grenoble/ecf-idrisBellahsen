package com.example.api.entity;

import java.util.Set;

import javax.persistence.*;
import com.example.api.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JoinTable(name = "category_book",
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))    
    @JsonIgnoreProperties("categorys")
    private Set<Book> books;




}
