package com.wims.whereismystore.Class;
/*
 * 작성자 : 도 희
 * 최초 작성일자 : 2020.12.03
 *
 * 게시글 클래스
 */

public class Post {
    private String pin;//작성글 고유 번호
    private String title;//작성글 제목
    private String postText;//작성글 내용
    private String createDate;//작성 시간
    private String address;//작성자 입력 주소
    private String district_code; //시군구 구분 코드
    private String local_code;//지역 구분 코드
    private String writerPin;//작성자 핀
    private String BLNumber;// 사업자 번호

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

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
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

    public String getLocal_code() {
        return local_code;
    }

    public void setLocal_code(String local_code) {
        this.local_code = local_code;
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

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

}
