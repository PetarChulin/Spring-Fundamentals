package com.example.mymusicdb.model.dto;

import com.example.mymusicdb.model.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @Size(min = 5, message = "Image url must be more than 5 characters")
    private String imageUrl;

    @Size(min = 5, message = "Description length must be more than 5 characters")
    private String description;

    @Positive(message = "Copies must be positive")
    private Integer copies;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @PastOrPresent(message = "Release date cannot be in the future")
    @NotNull
    private LocalDate releasedDate;

    private String producer;

    @NotNull(message = "You must select genre")
    private String genre;

    @NotNull(message = "You must select artist")
    private SingerEnum singer;

    public AlbumDTO() {
    }

//    public AlbumDTO(Album album) {
//        this.id = album.getId();
//        this.name = album.getName();
//        this.imageUrl = album.getImageUrl();
//        this.description = album.getDescription();
//        this.copies = album.getCopies();
//        this.price = album.getPrice();
//        this.releasedDate = album.getReleasedDate();
//        this.producer = album.getProducer();
//        this.genre = album.getGenre();
//        this.singer = album.getArtist();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public SingerEnum getSinger() {
        return singer;
    }

    public void setSinger(SingerEnum singer) {
        this.singer = singer;
    }
}
