/**
 * Class name: Photo
 *
 * version 1.0
 *
 * Date: March 9, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 *
 */
package com.example.ibookit.Model;

/**
 * @version 1.0
 */
public class Photo {
    private String path;
    private Integer height;
    private Integer weight;

    /**
     * getter and setter
     *
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


}
