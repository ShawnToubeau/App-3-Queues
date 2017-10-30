package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {
    //list of all station objects
    static ArrayList<Station> stationObjectList = new ArrayList<>();
    //list of all train objects
    static ArrayList<Train> trainObjectList = new ArrayList<>();
    //list of all station locations
    static ArrayList<Integer> stationLocationList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //random number variable
        int randomNum = ThreadLocalRandom.current().nextInt(1,  2);

        //buffer writer for the logger
//        BufferedWriter writer = new BufferedWriter(new FileWriter("logger.txt"));
        PrintWriter writer = new PrintWriter("TrainSimulation.log");

        //configuration settings
        Configuration configuration = new Configuration("./TrainSimulation.config");

        //adds station locations from configuration object to station location list
        for (int index : configuration.getStations())
        {
            stationLocationList.add(index);
        }//end for

        //sets number of ticks
        int numOfTicks = configuration.getTicks();

        //length of train route
        int routeLength = configuration.getRoute().length;
        //style of train route
        String routeStyle = configuration.getRoute().style.toString().toLowerCase();

        //creates train route
        TrainRoute trainRoute = new TrainRoute(routeStyle, routeLength);

        //iterates through all the stations specified in configuration
        for (int i = 0; i < configuration.getStations().length; i++)
        {
            int currentStationLocation = configuration.getStations()[i];
            //generates a new station object
            //arguments: current station location, list of all station locations
            generateStations(currentStationLocation, stationLocationList);
        }//end for

        //iterates through all the trains specified in configuration
        for (int i = 0; i < configuration.getTrains().length; i++)
        {

            String direction = configuration.getTrains()[i].direction.toString().toLowerCase();
            int location = configuration.getTrains()[i].location;
            int capacity = configuration.getTrains()[i].capacity;
            //generates a new train object
            //arguments: train direction, location, max capacity, list of all station locations
            generateTrains(direction, location, capacity, stationLocationList);
        }//end for

        //main simulation
        while (numOfTicks >= 0)
        {
            writer.print("\n-----Running Simulation-----\n");
//            for (int i = 0; i < stationObjectList.size(); i++)
//            {
//                System.out.print(configuration.getStations()[i]+" ");
//            }//end for
//            System.out.print("\n");


            //iterates through all the trains
            for (int i = 0; i < trainObjectList.size(); i++)
            {
                //list of removed passenger's IDs
                ArrayList<Integer> removedPassenger = new ArrayList<>();
                //list of added passenger's IDs
                ArrayList<Integer> addedPassenger = new ArrayList<>();

                //current train object
                Train currentTrainObject = trainObjectList.get(i);
                //checks if the train is at a station
                currentTrainObject.checkIfAtStation(stationObjectList);
                //run if at a station
                if (currentTrainObject.currentStation != null)
                {
                    //disembarks passengers to station
                    removedPassenger = currentTrainObject.disembark();
                    //embarks passengers on to train
                    addedPassenger = currentTrainObject.embark();
                }//end if

                System.out.println("Train #"+currentTrainObject.getTrainID()+" is at location "+currentTrainObject.getPosition());
                if (currentTrainObject.getCurrentStation() != -1) {
                    System.out.println("Train #"+currentTrainObject.getTrainID()+" is at station "+currentTrainObject.getCurrentStation());
                }

                writer.print("\n\nTrain ID#"+currentTrainObject.getTrainID());
                writer.print("\n    Position:"+currentTrainObject.getPosition());
                writer.print("\n    Inbound? "+currentTrainObject.getDirection());
                writer.print("\n    Max Capacity "+currentTrainObject.getMaxCapacity());
                writer.print("\n    Current Capacity "+currentTrainObject.trainStorage.size());
                writer.print("\n    Current Station "+currentTrainObject.getCurrentStation());
                writer.print("\n        Removing Passengers..");
                writer.print("\n            Removed Passenger #"+removedPassenger);
                writer.print("\n        Adding Passengers..");
                writer.print("\n            Added Passenger #"+addedPassenger);

                //moves the train one space
                currentTrainObject.moveTrain(trainRoute);
            }//end for

            //iterates through all the stations
            for (int i = 0; i < stationObjectList.size(); i++)
            {
                //executes a random number of times
                for (int j = 0; j < randomNum; j++)
                {
                    //adds a new passenger to the currently selected stations
                    stationObjectList.get(i).addPassengers(stationLocationList);
                }//end for
            }//end for

            //decrements the number of ticks
            numOfTicks--;
            writer.print("\n\n-----End Simulation-----\n");
        }//end while

        //finishes writing to the log
        writer.close();
    }

    //method for creating new stations
    public static void generateStations(int stationLocation, ArrayList<Integer> stationLocationList) {
        //creates station objects
        //arguments: station location, list of all the station locations
        Station station = new Station(stationLocation, stationLocationList);
        //add station object to object list
        stationObjectList.add(station);
    }

    //method for creating new trains
    public static void generateTrains(String direction, int location, int capacity, ArrayList<Integer> stationLocationList) {
        //creates train objects, adds them to trainObjectList
        //arguments: train direction, location, max capacity, list of all the station locations
        Train train = new Train(direction, location, capacity, stationLocationList);
        //add train object to object list
        trainObjectList.add(train);
    }
}
