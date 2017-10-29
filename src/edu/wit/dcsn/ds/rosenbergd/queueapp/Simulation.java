package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Simulation {

    //list of all station objects
    static ArrayList<Station> stationObjectList = new ArrayList<>();
    //list of all train objects
    static ArrayList<Train> trainObjectList = new ArrayList<>();
    //list of all station locations
    static ArrayList<Integer> stationLocationList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException{

        int randomNum = ThreadLocalRandom.current().nextInt(1,  2);

        //writer for the logger
        BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt"));

        //configuration settings
        Configuration configuration = new Configuration("./TrainSimulation.config");

        //adds station locations from config to station location list
        for (int index : configuration.getStations()) {
            stationLocationList.add(index);
        }

        //sets number of ticks
        int numOfTicks = configuration.getTicks();

        //length of train route
        int routeLength = configuration.getRoute().length;
        //style of train route
        String routeStyle = configuration.getRoute().style.toString().toLowerCase();

        //creates train route
        TrainRoute trainRoute = new TrainRoute(routeStyle, routeLength);

        //creates stations
        for (int i = 0; i < configuration.getStations().length; i++) {
            int currentStationLocation = configuration.getStations()[i];
            generateStations(currentStationLocation, stationLocationList);
        }

        //creates trains
        for (int i = 0; i < configuration.getTrains().length; i++) {
            String direction = configuration.getTrains()[i].direction.toString().toLowerCase();
            int location = configuration.getTrains()[i].location;
            int capacity = configuration.getTrains()[i].capacity;
            generateTrains(direction, location, capacity, stationLocationList);
        }

        //main simulation
        while (numOfTicks >= 0) {
            //do stuff

            System.out.println("\n-----Running Simulation-----\n");
            for (int i = 0; i < stationObjectList.size(); i++) {
                System.out.print(configuration.getStations()[i]+" ");
            }
            System.out.print("\n");


//            for (int i = 0; i < stationList.size(); i++) {
//                if (stationList.get(i).hasQueuedPassengers()) {
//                    Passenger queuedPassenger = stationList.get(i).nextPassenger();
//                    System.out.println("Station #"+stationList.get(i).getStationID());
//                    System.out.println("  Passenger ID:"+queuedPassenger.getPassengerID());
//                    System.out.println("    Source "+queuedPassenger.getSource());
//                    System.out.println("    Destination "+queuedPassenger.getDestination());
//                } else {
//                    System.out.println("Station #"+stationList.get(i).getStationID()+" OUT OF PASSENGERS");
//                }
//            }

            for (int i = 0; i < trainObjectList.size(); i++) {
                int disembarkNum = 0;
                int embarkNum = 0;
                Train train = trainObjectList.get(i);
                train.checkIfAtStation(stationObjectList);
                if (train.currentStation != null) {
                    System.out.println("Removed passengers *********"+train.disembark());
                    System.out.println("Added passengers --------- "+train.embark());
//                    while (train.trainStorage.size() < train.maxCapacity && train.currentStation.hasQueuedPassengers()) {
//                        train.trainStorage.add(train.currentStation.nextPassenger());
//                        embarkNum++;
//                    }
                }
                    System.out.println("Train ID: "+train.getTrainID());
                    System.out.println("    Position:"+train.getPosition());
                    System.out.println("    Inbound? "+train.getDirection());
                    System.out.println("    Max Capacity "+train.getMaxCapacity());
                    System.out.println("    Current Capacity "+train.trainStorage.size());
                    System.out.println("    Current Station "+train.getCurrentStation());


                train.moveTrain(trainRoute);
            }

            for (int i = 0; i < stationObjectList.size(); i++) {
                for (int j = 0; j < randomNum; j++) {
                    stationObjectList.get(i).addPassengers(stationLocationList);
                }
            }

            numOfTicks--;
        }
        writer.close();
    }

    //creates station objects, adds them to list
    public static void generateStations(int stationLocation, ArrayList<Integer> stationLocationList) {
        Station station = new Station(stationLocation, stationLocationList);
        stationObjectList.add(station);
    }

    public static void generateTrains(String direction, int location, int capacity, ArrayList<Integer> stationLocationList) {
        Train train = new Train(direction, location, capacity, stationLocationList);
        trainObjectList.add(train);
    }

}
