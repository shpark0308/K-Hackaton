package com.example.we_in.storage;

public class Images {
private boolean isFront;
    private int image;
    private String title;
    private String desc;

    public Images(boolean isFront, int image, String title, String desc) {
        this.isFront=isFront;
        this.image = image;
        this.title = title;
        this.desc = desc;
        //사진 저장 날짜 및 시간 String 추가-Adapter, MainActivity 수정
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
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
}
