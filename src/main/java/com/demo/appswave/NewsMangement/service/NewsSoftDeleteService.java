package com.demo.appswave.NewsMangement.service;

import com.demo.appswave.NewsMangement.entities.News;
import com.demo.appswave.NewsMangement.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NewsSoftDeleteService {

    @Autowired
    private NewsRepository newsRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight

    public void softDeleteExpiredNews() {
        LocalDate currentDate = LocalDate.now();
        List<News> newsToBeDeleted = newsRepository.newToBeDeleted(currentDate);

        for (News news : newsToBeDeleted) {
            news.setDeleted(true);
            newsRepository.save(news);
        }
    }
}