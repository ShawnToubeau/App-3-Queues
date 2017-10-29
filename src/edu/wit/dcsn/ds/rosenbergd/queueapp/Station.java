package edu.wit.dcsn.ds.rosenbergd.queueapp;

import com.pearson.carrano.ArrayQueue;
import com.pearson.carrano.QueueInterface;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Station {
    
    QueueInterface<Passenger> queue = new ArrayQueue<>();

    int stationID;
    int randomNum = ThreadLocalRandom.current().nextInt(1,  25);

    //creates station object, arguments: (stationID, list of stations)
    Station(int stationID, ArrayList<Integer> stationLocationList){


        this.stationID = stationID; //stationID serves as station location

        //call addPassenger() a random number of times
        //arguments: arraylist of station locations
        for (int i = 0; i < randomNum; i++) {
            addPassengers(stationLocationList);
        }
    }

    public int getStationID() {
        return this.stationID;
    }

    //adds a passenger object to the station queue
    //arguments: arraylist of station locations
    public void addPassengers(ArrayList<Integer> stationLocationList) {

        //creates new passenger object
        //arguments: this station's location, list of station locations
        Passenger passenger = new Passenger(this.stationID, stationLocationList);

        //adds passenger object to queue
        queue.enqueue(passenger);
    }

    //gets next passenger in queue
    public Passenger nextPassenger() {
        return queue.dequeue();
    }

    //unnecessary?
    public QueueInterface<Passenger> getQueue() {
        return queue;
    }

    //checks if queue is empty
    public boolean hasQueuedPassengers() {
        return !queue.isEmpty();
    }
}
