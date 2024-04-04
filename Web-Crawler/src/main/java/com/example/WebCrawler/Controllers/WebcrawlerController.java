package com.example.WebCrawler.Controllers;

import com.example.WebCrawler.Entities.News;
import com.example.WebCrawler.Repositories.NewsRepository;
import com.example.WebCrawler.Services.WebcrawlerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class WebcrawlerController {

    private NewsRepository newsRepository = null;

    public WebcrawlerController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/fetch-news")
    public List<News> fetchNews() {
        // Generate sample news
        List<News> newsList = new ArrayList<>();
        
        Map<String, List<String>> newsMap = WebcrawlerService.getTitles();

        for (Map.Entry<String, List<String>> entry : newsMap.entrySet()) {
            String url = entry.getKey();
            List<String> newsTitles = entry.getValue();

            for(String newsTitle : newsTitles) {
                newsList.add(new News(url, newsTitle));
            }
        }

        // Save the news items to the database
        newsRepository.saveAll(newsList);

        return newsList;
    }
}
