package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.util.ArrayList;
import java.util.Random;

public class Passenger {

    //counter used to assign unique ID
    static int counter;

    int passengerID;
    int source;
    int destination;

    //passenger constructor
    //arguments: starting station, list of station locations
    Passenger(int startingStation, ArrayList<Integer> stationLocationList)
    {

        ArrayList<Integer> stationListCopy = new ArrayList<>(stationLocationList);

        //assigns unique ID to passenger
        this.passengerID = counter++;

        //sets the passengers starting station
        this.source = startingStation;

        //removes the passenger's starting location from list of station locations
        stationListCopy.remove(Integer.valueOf(this.source));
        //sets passenger destination as random station location
        this.destination = stationListCopy.get(new Random().nextInt(stationListCopy.size()));
    }//end passenger constructor

    //gets passenger's ID
    public int getPassengerID() {
        return this.passengerID;
    }

    //gets passenger's starting station
    public int getSource() {
        return this.source;
    }

    //gets passenger's destination
    public int getDestination() {
        return this.destination;
    }
}
