/**
 * Class name: PointCountContainer
 *
 * version 1.1
 *
 * Date: March 20, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Functionality;

import java.util.HashMap;

/**
 * @author zijun wu
 *
 * @version 1.1
 */

public class PointCountContainer {

    private Double point;
    private Integer count;
    private HashMap<String, Double> points;
    private HashMap<String, Integer> counts;

    /**
     * Constructor
     * @param point
     * @param count
     */
    public PointCountContainer(Double point, Integer count) {
        this.point = point;
        this.count = count;
    }

    /**
     * Constructor
     * @param points
     * @param counts
     */
    public PointCountContainer(HashMap<String, Double> points, HashMap<String, Integer> counts) {
        this.points = points;
        this.counts = counts;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public HashMap<String, Double> getPoints() {
        return points;
    }

    public void setPoints(HashMap<String, Double> points) {
        this.points = points;
    }

    public HashMap<String, Integer> getCounts() {
        return counts;
    }

    public void setCounts(HashMap<String, Integer> counts) {
        this.counts = counts;
    }
}
