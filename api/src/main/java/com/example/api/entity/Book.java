package com.example.api.entity;

import java.util.Set;

import javax.persistence.*;
import com.example.api.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "book")
public class Book {
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

    @Column(name = "title", nullable = false)
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

 
    @Column(name = "picture",  nullable = false)
    private String picture;

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    
    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private Set<Chapter> chapter;

    public Set<Chapter> getchapter() {
        return this.chapter;
    }

    public void setChapter(Set<Chapter> chapters) {
        this.chapter = chapters;
    }

    @ManyToMany(fetch = FetchType.LAZY,
    mappedBy = "books",
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JsonIgnoreProperties("books")
    private Set<Category> categorys;
}
