package com.example.sec_project;

public class TouristPlaces {

    private int img;
    private String name;
    private String site;
    private int rating;

    public TouristPlaces(int img, String name, String site, int rating) {
        this.img = img;
        this.name = name;
        this.site = site;
        this.rating = rating;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
