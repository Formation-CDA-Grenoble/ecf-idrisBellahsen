package com.example.api.entity;

import java.util.Set;

import javax.persistence.*;

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


    
    @OneToMany(mappedBy = "chapter")
    @JsonIgnoreProperties("chapter")
    private Set<Chapter> articles;

    public Set<Chapter> getArticles() {
        return this.articles;
    }

    public void setArticles(Set<Chapter> articles) {
        this.articles = articles;
    }


}
