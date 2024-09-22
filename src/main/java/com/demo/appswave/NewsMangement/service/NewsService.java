package com.demo.appswave.NewsMangement.service;

import com.demo.appswave.NewsMangement.entities.News;
import com.demo.appswave.NewsMangement.entities.Role;
import com.demo.appswave.NewsMangement.enumeration.ERole;
import com.demo.appswave.NewsMangement.enumeration.NewsStatus;
import com.demo.appswave.NewsMangement.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAllActiveNews();
    }

    public News addNews(News news) {
        news.setStatus(NewsStatus.PENDING);
        return newsRepository.save(news);
    }

    public List<News> getApprovedNews() {
        return newsRepository.findByStatus(NewsStatus.APPROVED);
    }
    public News updateNews(Long id, News updatedNews) throws Exception {
        Optional<News> existingNews = newsRepository.findById(id);
        if (existingNews.isPresent()) {
            News news = existingNews.get();
            news.setTitle(updatedNews.getTitle());
            news.setTitleArabic(updatedNews.getTitleArabic());
            news.setDescription(updatedNews.getDescription());
            news.setDescriptionArabic(updatedNews.getDescriptionArabic());
            news.setPublishDate(updatedNews.getPublishDate());
            news.setImageURL(updatedNews.getImageURL());
            return newsRepository.save(news);
        } else {
            throw new Exception("News not found with id " + id);
        }
    }
    public void deleteNews(Long id) throws Exception {
        News news =  newsRepository.findById(id)
                .orElseThrow(() -> new Exception("News not found with id " + id));
        if (news.getStatus().equals(NewsStatus.APPROVED)) {
            if (!isAdmin()) {
                throw new AccessDeniedException("Only admins can delete approved news items.");

            } else {
                newsRepository.delete(news);
            }
        }
     else {
        newsRepository.delete(news);
    }
    }
    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ERole.ROLE_ADMIN));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteApprovedNews(News existingNews){
        try {
            newsRepository.delete(existingNews);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
