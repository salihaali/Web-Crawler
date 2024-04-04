package com.example.WebCrawler.Services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebcrawlerService {
    public static Map<String, List<String>> getTitles() {
        Map<String, String> urlCssSelectorMap = new HashMap<>();

        urlCssSelectorMap.put("https://novini.bg/", "h2.g-grid__item-title");
        urlCssSelectorMap.put("https://dnes.bg/", ".news-title, .top-news-slider-title");
        urlCssSelectorMap.put("https://news.bg/", ".topic-information .title");
        urlCssSelectorMap.put("https://vesti.bg/", ".leading-news .card-item h2 a");

        Map<String, List<String>> newsMap = new HashMap<>();

        for (Map.Entry<String, String> entry : urlCssSelectorMap.entrySet()) {
            String website = entry.getKey();
            List<String> titles = fetchAndParseTitles(website, entry.getValue());

            newsMap.put(website, titles);
        }

        return newsMap;
    }

    public static List<String> fetchAndParseTitles(String url, String titleSelector) {
        List<String> newsTitles = new ArrayList<>();

        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect(url).get();

            // Extract all <h1> tags from the document
            Elements titles = document.select(titleSelector);

            // Extract the text from each <h1> tag and add it to the list
            for (Element title : titles) {
                newsTitles.add(title.text());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception as per your requirement
        }

        return newsTitles;
    }

    // public static void writeListToFile(List<String> stringList, String filePath) throws IOException {
    //     // Create a BufferedWriter with UTF-8 encoding
    //     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));

    //     try {
    //         // Write each string from the list to the file
    //         for (String str : stringList) {
    //             writer.write(str);
    //             writer.newLine(); // Add a newline character after each string
    //         }
    //     } finally {
    //         // Close the writer
    //         if (writer != null) {
    //             writer.close();
    //         }
    //     }
    // }
}
