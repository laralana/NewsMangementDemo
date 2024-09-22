package com.demo.appswave.NewsMangement.repository;

import com.demo.appswave.NewsMangement.entities.News;
import com.demo.appswave.NewsMangement.enumeration.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News,Long> {
    @Query("SELECT n FROM News n WHERE n.deleted = false")
    List<News> findAllActiveNews();
    @Query("SELECT n FROM News n WHERE n.publishDate < :currentDate AND n.deleted = false")
    List<News> newToBeDeleted(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT n FROM News n WHERE n.status = :status AND n.deleted = false")
    List<News> findByStatus(@Param("status") NewsStatus status);

    @Query("SELECT n FROM News n WHERE n.id = :id AND n.deleted = false")
    Optional<News> findById(@Param("id") Long id);

}
