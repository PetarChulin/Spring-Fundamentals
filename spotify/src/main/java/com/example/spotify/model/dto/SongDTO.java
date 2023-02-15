package com.example.spotify.model.dto;

import com.example.spotify.model.entity.Song;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class SongDTO {

    private Long id;
    @Size(min = 3 , max = 20 , message = "Performer name length must be between 3 and 20 characters!")
    @NotNull
    private String performer;

    @Size(min = 2 , max = 20 , message = "Title length must be between 2 and 20 characters!")
    @NotNull
    String title;

    @Positive(message = "Duration must be positive!")
    private Integer duration;

    @PastOrPresent(message = "The date can not be in the future!")
    private LocalDate releaseDate;

    @NotBlank(message = "You must select a style!")
    private String style;

    public SongDTO() {
    }

    public SongDTO(Song song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = song.getDuration();
        this.releaseDate = song.getReleaseDate();
        this.style = song.getStyle().getStyle().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
