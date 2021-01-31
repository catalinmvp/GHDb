package com.example.ghdb;


//clasa normala in java de modificat in functie de ce am nevoie

import java.io.Serializable;

public class GameModelClass implements Serializable {

    String id;
    String name;
    String img;
    String genre;
    String genre2;
    String releaseDate;
    String prize;
    String review;
    String Link;
    Float numar;

    public GameModelClass(String id, String name, String img, String genre, String genre2, String releaseDate, String prize, String review, String link, Float numar) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.genre = genre;
        this.genre2 = genre2;
        this.releaseDate = releaseDate;
        this.prize = prize;
        this.review = review;
        Link = link;
        this.numar = numar;
    }

    public GameModelClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre2() {
        return genre2;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Float getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        float f = Float.parseFloat(numar);
        this.numar = f;
    }
}
