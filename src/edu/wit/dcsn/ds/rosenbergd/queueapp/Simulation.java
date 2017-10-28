package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Simulation {

    //list of all station objects
    static ArrayList<Station> stationList = new ArrayList<>();
    //list of all train objects
    static ArrayList<Train> trainList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        Configuration configuration = new Configuration("./TrainSimulation.config");

        //sets number of ticks
        int numOfTicks = configuration.getTicks();

        //creates stations
        for (int i = 0; i < configuration.getStations().length; i++) {
            generateStations(configuration.getStations()[i], configuration.getStations());
        }

//        for (int i = 0; i < configuration.getTrains().length; i++) {
//            generateTrains(configuration.getStations()[i]);
//        }


        //main simulation
        while (numOfTicks >= 0) {
            //do stuff

            System.out.println("\n-----Running Simulation-----\n");

            for (int i = 0; i < stationList.size(); i++) {
                if (stationList.get(i).hasQueuedPassengers()) {
                    Passenger queuedPassenger = stationList.get(i).nextPassenger();
                    System.out.println("Station #"+stationList.get(i).getStationID());
                    System.out.println("  Passenger ID:"+queuedPassenger.getPassengerID());
                    System.out.println("    Source "+queuedPassenger.getSource());
                    System.out.println("    Destination "+queuedPassenger.getDestination());
                } else {
                    System.out.println("Station #"+stationList.get(i).getStationID()+" OUT OF PASSENGERS");
                }
            }

            numOfTicks--;
        }
    }

    //creates station objects, adds them to list
    public static void generateStations(int stationLocation, int[] configStationList) {
        Station station = new Station(stationLocation, configStationList);
        stationList.add(station);
    }

//    public static void generateTrains(boolean inbound, int location, int startingCapacity) {
//        Train train = new Train(inbound, location, startingCapacity);
//        trainList.add(train);
//    }

}
