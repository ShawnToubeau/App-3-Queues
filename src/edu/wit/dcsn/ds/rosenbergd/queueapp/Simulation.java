package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Simulation {

    //list of all station objects
    static ArrayList<Station> stationObjectList = new ArrayList<>();
    //list of all train objects
    static ArrayList<Train> trainObjectList = new ArrayList<>();

    static ArrayList<Integer> stationLocationList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException{

        BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt"));

        Configuration configuration = new Configuration("./TrainSimulation.config");

        for (int index : configuration.getStations()) {
            stationLocationList.add(index);
        }

        //sets number of ticks
        int numOfTicks = configuration.getTicks();

        String routeStyle = configuration.getRoute().style.toString().toLowerCase();
        int routeLength = configuration.getRoute().length;
        TrainRoute trainRoute = new TrainRoute(routeStyle, routeLength);
//        int[] stationLocationList = configuration.getStations();

        //creates stations
        for (int i = 0; i < configuration.getStations().length; i++) {
            int currentStationLocation = configuration.getStations()[i];
//            int[] stationLocationList = configuration.getStations();
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
//        numOfTicks = 0;

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
                Train train = trainObjectList.get(i);
                train.checkIfAtStation(stationObjectList);
                    System.out.println("Train ID: "+train.getTrainID());
                    System.out.println("    Position:"+train.getPosition());
                    System.out.println("    Inbound? "+train.getDirection());
                    System.out.println("    Max Capacity "+train.getMaxCapacity());
                    System.out.println("    Current Capacity "+train.trainStorage.size());
                    System.out.println("    Current Station "+train.getCurrentStation());
                        System.out.println("    Passenger ID: "+train.trainStorage.get(0).getPassengerID());
                        System.out.println("    Passenger Src: "+train.trainStorage.get(0).getSource());
                        System.out.println("    Passenger Dest: "+train.trainStorage.get(0).getDestination());

                train.moveTrain(trainRoute);


            }
//            for (int i = 0; i < trainObjectList.size(); i++) {
//                trainObjectList.get(i).moveTrain(stationObjectList, trainRoute);
//            }

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
