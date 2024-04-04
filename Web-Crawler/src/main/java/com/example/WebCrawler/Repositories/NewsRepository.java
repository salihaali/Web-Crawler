package com.example.WebCrawler.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.WebCrawler.Entities.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
}

