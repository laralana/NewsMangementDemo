package com.demo.appswave.NewsMangement.controller;

import com.demo.appswave.NewsMangement.entities.News;
import com.demo.appswave.NewsMangement.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }


    @PostMapping
    public News addNews(@RequestBody News news) {
        return newsService.addNews(news);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateNews(@PathVariable Long id, @RequestBody News updatedNews)  {
        try {
            return ResponseEntity.ok(newsService.updateNews(id, updatedNews));
        } catch (Exception e) {
            logger.error(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id)  {
        try {
            newsService.deleteNews(id);
            return ResponseEntity.ok("News deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete news: " + e.getMessage());
        }
    }
    @GetMapping("/approved")
    public List<News> getApprovedNews() {
        return newsService.getApprovedNews();
    }

}