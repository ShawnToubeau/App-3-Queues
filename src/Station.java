import com.pearson.carrano.ArrayQueue;
import com.pearson.carrano.QueueInterface;

import java.util.ArrayList;

public class Station {
    
    QueueInterface<Passenger> queue = new ArrayQueue<>();

    int stationID;

    Station(int stationID){
        this.stationID = stationID;
    }

    public int getStationID() {
        return this.stationID;
    }

    public void addPassengers(ArrayList<Integer> stationList) {
        Passenger passenger = new Passenger(this.stationID, stationList);
        queue.enqueue(passenger);
    }

    public Passenger nextPassenger() {
        return queue.dequeue();
    }

}
