package com.example.sec_project;

public class DataClass {
    private String imageURL;
    private String caption;
    private String des;
    private String price;
    private String catog;
    private String pos;
    private Long rating;

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getCatog() {
        return catog;
    }

    public void setCatog(String catog) {
        this.catog = catog;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DataClass(String imageURL, String caption, String des, String price,String catog,String pos)
     {
        this.imageURL = imageURL;
        this.caption = caption;
        this.des = des;
        this.price = price;
         this.catog = catog;
         this.pos = pos;
    }
    public DataClass(){

    }


}
