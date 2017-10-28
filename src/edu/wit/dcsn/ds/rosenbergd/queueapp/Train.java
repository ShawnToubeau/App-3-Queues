package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.util.ArrayList;

/**
 * Moves the train 
 * @author Erich Hauntsman
 *
 */
public class Train {
	
	ArrayList<Passenger> trainStorage = new ArrayList<>();

	int trainID;
	int position;
//	int currentCapacity;
	int maxCapacity;
	static int counter;
	Station currentStation;
	boolean inbound = false;

	public Train(boolean inbound, int position, Passenger startingCapacity){
		this.trainID = counter++;
		this.position = position;
		this.trainStorage.add(startingCapacity);

		if (this.inbound == inbound) {
			this.inbound = true;
		}
	}//end Train

	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

	public Station getCurrentStation() {
		return this.currentStation;
	}

	
	public void moveTrain(ArrayList<Station> stationCollection, TrainRoute trainRoute){

		int track1End = trainRoute.length/2;
		int track2Start = trainRoute.length/2+1;

		if (trainRoute.style == "circular") {
			if (this.position == trainRoute.length) {
				this.position = 0;
			}
			else {
				this.position++;
			}
		} else if (trainRoute.style == "linear") {
			if (this.position == track1End) {
				this.position = track2Start;
			} else if (this.position == trainRoute.length) {
				this.position = 0;
			} else {
				this.position++;
			}
		}


		for (int i = 0; i < stationCollection.size(); i++) {
			if (this.position == stationCollection.get(i).stationID) {
				setCurrentStation(stationCollection.get(i));
				disembark(this.currentStation.getStationID());
				board(this.currentStation.nextPassenger());
			}
		}
	}//end moveTrain
	
	public void disembark(int currentStation){

		for (int i = 0; i < this.trainStorage.size(); i++){
			if (this.trainStorage.get(i).getPassengerID() == currentStation){
				this.trainStorage.remove(this.trainStorage.get(i));
			}
		}//end while
	}//end disembark
	
	public void board(Passenger passenger){
		while (this.trainStorage.size() <= this.maxCapacity || this.currentStation.hasQueuedPassengers()){
			this.trainStorage.add(passenger);
		}
	}//end board
}//end Train class
