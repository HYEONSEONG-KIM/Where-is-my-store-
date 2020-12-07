package com.wims.whereismystore.Class;
/*
 * 작성자 : 도 희
 * 최초 작성일자 : 2020.12.03
 *
 * 게시글 클래스
 */

import android.net.Uri;

import java.util.ArrayList;

public class Post {
    private String pin;//글 고유 번호
    private String title;//글 제목
    private String contents;//글 내용
    private String createDate;//작성 시간
    private String modifyDate;//수정 시간
    private String address;//작성자 입력 주소
    private String districtName; //시군구 구분
    private String localName;//지역 구분
    private String writerPin;//작성자 핀
    private String name;//작성자 이름
    private String BLNumber;// 사업자 번호
    private String state;//글의 상태(1. 정상, 2. 예약중, 3. 거래 완료)
    private String report;//신고 (1. 정상, 2. 신고 중, 3. 거래 중지)
    private String price;//가격
    private String photo;//사진

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }


    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWriterPin() {
        return writerPin;
    }

    public void setWriterPin(String writerPin) {
        this.writerPin = writerPin;
    }

    public String getBLNumber() {
        return BLNumber;
    }

    public void setBLNumber(String BLNumber) {
        this.BLNumber = BLNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

}
