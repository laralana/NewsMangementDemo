package com.demo.appswave.NewsMangement.entities;

import com.demo.appswave.NewsMangement.enumeration.NewsStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 150)
    @Column(name = "Title", length = 150)
    private String title;
    @Size(max = 150)
    @Column(name = "Title_Arabic", length = 150)
    private String titleArabic;
    @Size(max = 500)
    @Column(name = "Description", length = 500)
    private String Description;
    @Size(max = 500)
    @Column(name = "Description_Arabic", length = 500)
    private String descriptionArabic;
    @Column(name = "Publish_Date")
    private LocalDate publishDate;
    @Size(max = 150)
    @Column(name = "ImageURL", length = 150)
    private String imageURL;
    @Enumerated(EnumType.STRING)
    private NewsStatus status;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private boolean deleted = false; // Soft delete flag

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleArabic() {
        return titleArabic;
    }

    public void setTitleArabic(String titleArabic) {
        this.titleArabic = titleArabic;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescriptionArabic() {
        return descriptionArabic;
    }

    public void setDescriptionArabic(String descriptionArabic) {
        this.descriptionArabic = descriptionArabic;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
