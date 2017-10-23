import java.util.ArrayList;
import java.util.Random;

public class Passenger {

    static int counter;

    int passengerID;
    int source;
    int destination;

    Passenger(int startingStation, ArrayList<Integer> stationList) {

        ArrayList<Integer> stationListCopy = new ArrayList<>(stationList);

        this.passengerID = counter++;
        this.source = startingStation;
        stationListCopy.remove(Integer.valueOf(this.source));
        this.destination = stationListCopy.get(new Random().nextInt(stationListCopy.size()));
    }

    public int getPassengerID() {
        return this.passengerID;
    }

    public int getSource() {
        return this.source;
    }

    public int getDestination() {
        return this.destination;
    }

}
