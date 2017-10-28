package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.util.ArrayList;

/**
 * Sets the collections for trains and stations and determines the train's route
 * @author Erich Hauntsman
 *
 */
public class TrainRoute {

    int length;
    String style;

    public TrainRoute(int length, String style) {
        this.length = length;
        this.style = style;
    }
    ArrayList<Object> route1 = new ArrayList<>();
    ArrayList<Object> stationCollection = new ArrayList<>();
    //collection for trains
    //collection for stations
}
