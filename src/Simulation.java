import java.util.ArrayList;

public class Simulation {

    public static ArrayList<Integer> stationList = new ArrayList<>();

    public static void main(String[] args) {

        stationList.add(1);
        stationList.add(2);
        stationList.add(3);
        stationList.add(6);

        Station station1 = new Station(stationList.get(0));
        Station station2 = new Station(stationList.get(2));


        station1.addPassengers(stationList);
        station1.addPassengers(stationList);
        station1.addPassengers(stationList);
        station1.addPassengers(stationList);

        station2.addPassengers(stationList);
        station2.addPassengers(stationList);

        while (!station1.queue.isEmpty()) {
            Passenger passenger = station1.nextPassenger();

            System.out.println("Passenger: ID: "+passenger.getPassengerID()+" Source: "+passenger.getSource()+" Destination: "+passenger.getDestination());
        }

        while (!station2.queue.isEmpty()) {
            Passenger passenger = station2.nextPassenger();

            System.out.println("Passenger: ID: "+passenger.getPassengerID()+" Source: "+passenger.getSource()+" Destination: "+passenger.getDestination());
        }
    }
}
