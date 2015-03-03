package com.teamvoy.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by OLEG on 10.02.2015.
 */
@DatabaseTable(tableName = "movie")
public class Movie {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String title;

    @DatabaseField
    String description;

    @DatabaseField
    String date;

    @DatabaseField
    double rate;

    @DatabaseField
    int imageId;

    @DatabaseField
    int popularity;

    public Movie() {
    }

    public Movie(String title, String date, String descpription, double rate, int imageId,
                 int popularity) {
        this.title = title;
        this.description = descpription;
        this.date = date;
        this.rate = rate;
        this.imageId = imageId;
        this.popularity = popularity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        String movieInfo = "Title : " + this.getTitle() + "\n" +
                "Date : " + this.getDate() + "\n" +
                "Rate : " + this.getRate() + "\n" +
                "Reviews : " + this.getPopularity() + "\n" +
                "Description : " + this.getDescription()  ;
        return movieInfo;
    }
}
