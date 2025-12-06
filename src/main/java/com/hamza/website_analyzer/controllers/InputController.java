package com.hamza.website_analyzer.controllers;

import com.hamza.website_analyzer.services.WebsiteAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/input")
public class InputController {

    @Autowired
    private WebsiteAnalyzerService websiteAnalyzerService;

    @PostMapping
    public Map<String, Object> handleInput(@RequestBody String input) throws IOException {
        System.out.println("Received input: " + input);
        Map<String, Object> analysis = websiteAnalyzerService.analyzeWebsite(input);
        System.out.println("Analysis result: " + analysis);
        return analysis;
    }
}
