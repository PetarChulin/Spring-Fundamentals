package com.example.mymusicdb.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SingerEnum name;

    @Column(name = "career_information" , columnDefinition = "LONGTEXT" , nullable = false)
    private String careerInformation;


    public Artist() {
    }

    public Artist(SingerEnum name, String careerInformation) {
         this.name = name;
         this.careerInformation = careerInformation;
    }

    public Artist(String name) {
        this.name = SingerEnum.valueOf(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SingerEnum getName() {
        return name;
    }

    public void setName(SingerEnum name) {
        this.name = name;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
