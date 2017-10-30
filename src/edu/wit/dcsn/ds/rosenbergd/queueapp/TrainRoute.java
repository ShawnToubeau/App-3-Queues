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

    public TrainRoute(String style, int length)
    {
        this.length = length;
        this.style = style;
    }//end TrainRoute constructor

    //returns train route style
    public String getStyle() {
        return this.style;
    }

    //returns train route length
    public int getLength() {
        return this.length;
    }
}
