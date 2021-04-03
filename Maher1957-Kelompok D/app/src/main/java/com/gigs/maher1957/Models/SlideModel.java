package com.gigs.maher1957.Models;

import com.google.gson.annotations.SerializedName;

public class SlideModel {

    /// here you can use string variable to store url
    // if you want to load image from the internet
    @SerializedName("addedTocart")
    private boolean addedTocart = false;
    private int mInteger;
    private String price ,textUkuran, name, price2, desc, disc,image, textView;

    public SlideModel(int mInteger) {
        this.mInteger = mInteger;
    }

    public void setTextUkuran(String textUkuran) { this.textUkuran = textUkuran; }

    public String getText(){ return textUkuran; }

    public void setImage(String image) { this.image = image; }

    public String getImage(){
        return image;
    }

    public int getMInteger() { return mInteger; }

    public void setMinteger(int mInteger) {
        this.mInteger = mInteger;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public boolean isAddedTocart() {
        return addedTocart;
    }

    public void setAddedTocart(boolean addedTocart) {
        this.addedTocart = addedTocart;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }
}
