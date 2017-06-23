package com.vovatkach2427gmail.eyeexercises.Model;

/**
 * Created by vovat on 02.06.2017.
 */

public class StatisticsModel {
    private int Id;
    private DateModel date;

    public StatisticsModel(int id, DateModel date) {
        Id = id;
        this.date = date;
    }

    public int getId() {
        return Id;
    }

    public DateModel getDate() {
        return date;
    }
}
