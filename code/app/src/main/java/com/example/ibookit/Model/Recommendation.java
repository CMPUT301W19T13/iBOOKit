/**
 * Class name: Recommendation
 *
 * version 1.1
 *
 * Date: March 20, 2019
 *
 * Copyright (c) Team 13, Winter, CMPUT301, University of Alberta
 */
package com.example.ibookit.Model;

/**
 * @author zijun wu
 *
 * @version 1.1
 */
public class Recommendation {

    private String username;
    private double Classics;
    private Integer ClassicsCount;
    private double SciFi;
    private Integer SciFiCount;
    private double Horror;
    private Integer HorrorCount;
    private double Thriller;
    private Integer ThrillerCount;
    private double Sports;
    private Integer SportsCount;
    private double Comics;
    private Integer ComicsCount;
    private double Romance;
    private Integer RomanceCount;
    private double Business;
    private Integer BusinessCount;
    private double other;
    private Integer otherCount;

    public Recommendation(String username){
        this.username = username;
        this.Classics = 100;
        this.SciFi = 100;
        this.Horror = 100;
        this.Thriller = 100;
        this.Sports = 100;
        this.Comics = 100;
        this.Romance = 100;
        this.Business = 100;
        this.other = 100;
        this.SciFiCount = 0;
        this.HorrorCount = 0;
        this.ThrillerCount = 0;
        this.SportsCount = 0;
        this.ComicsCount = 0;
        this.RomanceCount = 0;
        this.BusinessCount = 0;
        this.otherCount = 0;
    }

    public Recommendation(){}


    /**
     * Getter and Setter
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getClassics() {
        return Classics;
    }

    public void setClassics(double classics) {
        Classics = classics;
    }

    public double getSciFi() {
        return SciFi;
    }

    public void setSciFi(double sciFi) {
        SciFi = sciFi;
    }

    public double getHorror() {
        return Horror;
    }

    public void setHorror(double horror) {
        Horror = horror;
    }

    public double getThriller() {
        return Thriller;
    }

    public void setThriller(double thriller) {
        Thriller = thriller;
    }

    public double getSports() {
        return Sports;
    }

    public void setSports(double sports) {
        Sports = sports;
    }

    public double getComics() {
        return Comics;
    }

    public void setComics(double comics) {
        Comics = comics;
    }

    public double getRomance() {
        return Romance;
    }

    public void setRomance(double romance) {
        Romance = romance;
    }

    public double getBusiness() {
        return Business;
    }

    public void setBusiness(double business) {
        Business = business;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public Integer getClassicsCount() {
        return ClassicsCount;
    }

    public void setClassicsCount(Integer classicsCount) {
        ClassicsCount = classicsCount;
    }

    public Integer getSciFiCount() {
        return SciFiCount;
    }

    public void setSciFiCount(Integer sciFiCount) {
        SciFiCount = sciFiCount;
    }

    public Integer getHorrorCount() {
        return HorrorCount;
    }

    public void setHorrorCount(Integer horrorCount) {
        HorrorCount = horrorCount;
    }

    public Integer getThrillerCount() {
        return ThrillerCount;
    }

    public void setThrillerCount(Integer thrillerCount) {
        ThrillerCount = thrillerCount;
    }

    public Integer getSportsCount() {
        return SportsCount;
    }

    public void setSportsCount(Integer sportsCount) {
        SportsCount = sportsCount;
    }

    public Integer getComicsCount() {
        return ComicsCount;
    }

    public void setComicsCount(Integer comicsCount) {
        ComicsCount = comicsCount;
    }

    public Integer getRomanceCount() {
        return RomanceCount;
    }

    public void setRomanceCount(Integer romanceCount) {
        RomanceCount = romanceCount;
    }

    public Integer getBusinessCount() {
        return BusinessCount;
    }

    public void setBusinessCount(Integer businessCount) {
        BusinessCount = businessCount;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }
}
