/**
 * Moves the train 
 * @author Erich Hauntsman
 *
 */
public class Train {
	
	ArrayList<Integer> trainStorage = new ArrayList<>();
	
	int trainID;
	
	public Train(int trainID){
		this.trainID = counter++;
	}//end Train

	
	public void moveTrain(int length, int routeLength){
		if (this.position = routeLength){
			this.position = 0;			
		}
		else {
			this.position++;
		}
		
		if (this.position = stationID){
			disembark();
			board();
		}
		
	}//end moveTrain
	
	public void disembark(int currentCapacity){
		for (int i = 0; trainStorage.size(); i++){

		if (passenger.getPassenger.ID = station.getStationID()){
			trainStorage.remove(passenger.getPassengerID);
			currentCapacity--;
			}
		}//end while
	}//end disembark
	
	public void board(int currentCapacity, int maxCapacity){
		while (currentCapacity <= maxCapacity){
			trainStorage.add(passenger);
			currentCapactity++;
		}
	}//end board
}//end Train class
