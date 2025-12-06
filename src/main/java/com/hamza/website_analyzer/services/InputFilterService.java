package com.hamza.website_analyzer.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InputFilterService {
    public static String fetchWebsiteContent(String url) throws IOException {
        System.out.println("Received URL: " + url);

        try {
            // Fetch the website content
            Document document = Jsoup.connect(url).get();

            // Extract and return the website's title as an example
            return document.title();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid URL format: " + url, e);
        } catch (IOException e) {
            throw new IOException("Failed to fetch content from URL: " + url, e);
        }
    }


}
