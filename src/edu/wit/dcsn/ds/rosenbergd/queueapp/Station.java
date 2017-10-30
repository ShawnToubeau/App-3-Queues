package edu.wit.dcsn.ds.rosenbergd.queueapp;

import com.pearson.carrano.ArrayQueue;
import com.pearson.carrano.QueueInterface;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Station
{

    //passenger queue
    QueueInterface<Passenger> queue = new ArrayQueue<>();

    int stationID;
    int randomNum = ThreadLocalRandom.current().nextInt(1,  5);

    //creates station object, arguments: (stationID, list of stations)
    Station(int stationID, ArrayList<Integer> stationLocationList)
    {

        //stationID serves as station location
        this.stationID = stationID;

        //call addPassenger() a random number of times
        //arguments: arraylist of station locations
        for (int i = 0; i < randomNum; i++) {
            addPassengers(stationLocationList);
        }//end for
    }//end station constructor

    //return the station ID
    public int getStationID() {
        return this.stationID;
    }

    //adds a passenger object to the station queue
    //arguments: arraylist of station locations
    public void addPassengers(ArrayList<Integer> stationLocationList)
    {

        //creates new passenger object
        //arguments: this station's location, list of station locations
        Passenger passenger = new Passenger(this.stationID, stationLocationList);

        //adds passenger object to queue
        queue.enqueue(passenger);
    }//end addPassengers()

    //returns next passenger in queue
    public Passenger nextPassenger() {
        return queue.dequeue();
    }

    //checks if queue is empty
    public boolean hasQueuedPassengers() {
        return !queue.isEmpty();
    }
}//end station class
