package com.example.ibookit.Model;

/**
 * @author zijun wu
 *
 * @version 1.0
 */
public class MessageIBOOKit {

    private String mid;
    private String tile;
    private String content;

    /**
     * constructor
     *
     * @param tile
     * @param content
     */
    public MessageIBOOKit(String tile, String content) {
        this.tile = tile;
        this.content = content;
    }

    /**
     * default constructor
     */
    public MessageIBOOKit(){}


    /**
     * getter and setter
     *
     */
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
