import java.util.ArrayList;
import java.util.Collection;

/**
 * Moves the train 
 * @author Erich Hauntsman
 *
 */
public class Train {
	
	ArrayList<Passenger> trainStorage = new ArrayList<>();
	
	int trainID;
	int position;
	int currentCapacity;
	int maxCapacity;
	static int counter;
	
	public Train(int trainID){
		this.trainID = counter++;
	}//end Train

	
	public void moveTrain(int routeLength, ArrayList<Station> stationCollection){
		if (this.position == routeLength){
			this.position = 0;			
		}
		else {
			this.position++;
		}

		for (int i = 0; i < stationCollection.size(); i++) {
			if (this.position == stationCollection.get(i).stationID) {
				disembark(stationCollection);
				board();
			}
		}

		
	}//end moveTrain
	
	public void disembark(ArrayList<Station> stationCollection){
		for (int i = 0; i < trainStorage.size(); i++){

		if (trainStorage.get(i).getPassengerID() == stationCollection.get(i).getStationID()){
			trainStorage.remove(trainStorage.get(i));
			this.currentCapacity--;
			}
		}//end while
	}//end disembark
	
	public void board(){
		while (this.currentCapacity <= this.maxCapacity){
//			trainStorage.add(passenger);
			this.currentCapacity++;
		}
	}//end board
}//end Train class
