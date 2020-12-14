package com.wims.whereismystore.Class;

import android.net.Uri;

public class SaleListItem {
    private String title;
    private String districtName;
    private String price;
    private String state;
    private String postID;
    private String image;
    private String writerPin;

    public String getWriterPin() {
        return writerPin;
    }

    public void setWriterPin(String writerPin) {
        this.writerPin = writerPin;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

}
