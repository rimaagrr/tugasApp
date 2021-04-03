package com.gigs.maher1957.Models;

public class ListModel {

    private int image;
    private String title, desc, time;

    public ListModel(int image, String title, String desc, String time) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
