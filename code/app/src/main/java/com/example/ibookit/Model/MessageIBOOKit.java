package com.example.ibookit.Model;

public class MessageIBOOKit {

    private String mid;
    private String tile;
    private String content;
    private String status;

    public MessageIBOOKit(String tile, String content, String status) {
        this.tile = tile;
        this.content = content;
        this.status = status;
    }

    public MessageIBOOKit(){}

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
