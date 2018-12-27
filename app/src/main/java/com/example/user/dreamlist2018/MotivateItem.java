package com.example.user.dreamlist2018;

public class MotivateItem {

    private int id;
    private int dreamId;
    private String title;//例：家族写真
    private byte[] imageBytes;//画像
    private int isTrash;//1:ゴミ

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public int getIsTrash() {
        return isTrash;
    }

    public void setIsTrash(int isTrash) {
        this.isTrash = isTrash;
    }

    public int getDreamId() {
        return dreamId;
    }

    public void setDreamId(int dreamId) {
        this.dreamId = dreamId;
    }
}
