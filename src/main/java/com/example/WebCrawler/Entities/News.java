package com.example.WebCrawler.Entities;

import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private LocalDateTime timestamp;

    // Constructor to initialize timestamp
    public News() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor to initialize title, url, and timestamp
    public News(String title, String url) {
        this.title = title;
        this.url = url;
        this.timestamp = LocalDateTime.now();
    }
}
