package com.example.ibookit.Functionality;

import java.util.HashMap;

public class PointCountContainer {

    private Double point;
    private Integer count;
    private HashMap<String, Double> points;
    private HashMap<String, Integer> counts;

    public PointCountContainer(Double point, Integer count) {
        this.point = point;
        this.count = count;
    }

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
