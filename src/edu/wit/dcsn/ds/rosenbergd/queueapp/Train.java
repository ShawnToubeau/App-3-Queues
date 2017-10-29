package edu.wit.dcsn.ds.rosenbergd.queueapp;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Moves the train 
 * @author Erich Hauntsman
 *
 */
public class Train {
	
	ArrayList<Passenger> trainStorage = new ArrayList<>();

	int randomNum = ThreadLocalRandom.current().nextInt(1,  10);

	int trainID;
	int position;
	int maxCapacity;
	static int counter;
	boolean inbound = false;
	Station currentStation;

	public Train(String direction, int position, int maxCapacity, ArrayList<Integer> stationLocationList){

		this.trainID = counter++;
		this.position = position;
		this.maxCapacity = maxCapacity;

		if (direction.equals("inbound")) {
			this.inbound = true;
		} else {
			this.inbound = false;
		}

		for (int i = 0; i < randomNum; i++) {
			addPassengers(stationLocationList);
		}
	}//end Train

	public int getPosition() {
		return this.position;
	}

	public int getTrainID() {
		return this.trainID;
	}

	public boolean getDirection() {
		return inbound;
	}

	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	public int getCurrentStation() throws NullPointerException{
		try {
			return this.currentStation.stationID;
		} catch (NullPointerException ex) {
			return -1;
		}
	}

	
	public void moveTrain(TrainRoute trainRoute){

		resetCurrentStation();

		if (trainRoute.getStyle().equals("circular")) {
			if (this.inbound) {
				if (this.position == trainRoute.length) {
					this.position = 0;
				}
				else {
					this.position++;
				}
			} else if (!this.inbound) {
				if (this.position == 0) {
					this.position = 20;
				}
				else {
					this.position--;
				}
			}

		} else if (trainRoute.getStyle().equals("linear")) {
			if (this.inbound) {
				if (this.position == trainRoute.length) {
					this.inbound = false;
					this.position--;
				}
				else {
					this.position++;
				}
			} else if (!this.inbound) {
				if (this.position == 0) {
					this.inbound = true;
					this.position++;
				} else {
					this.position--;
				}
			}
		}
	}//end moveTrain

	public void checkIfAtStation(ArrayList<Station> stationList) {
		for (int i = 0; i < stationList.size(); i++) {
			if (this.position == stationList.get(i).stationID) {
				this.currentStation = stationList.get(i);
			}
		}
	}

	public void resetCurrentStation() {
		this.currentStation = null;
	}
	
	public int disembark(){
	    int disembarkCounter = 0;
		for (int i = 0; i < this.trainStorage.size(); i++){
			if (this.trainStorage.get(i).getDestination() == this.currentStation.getStationID()){
				this.trainStorage.remove(this.trainStorage.get(i));
				disembarkCounter++;
			}
		}//end while
        return disembarkCounter;
	}//end disembark

	public int embark(){
	    int embarkCounter = 0;
		while (this.trainStorage.size() < this.maxCapacity && this.currentStation.hasQueuedPassengers()){
			this.trainStorage.add(this.currentStation.nextPassenger());
			embarkCounter++;
		}
		return embarkCounter;
	}//end board

	//adds a passenger object to the station queue
	//arguments: arraylist of station locations
	public void addPassengers(ArrayList<Integer> stationList) {

		//creates new passenger object
		//arguments: this station's location, list of station locations
		Passenger passenger = new Passenger(0, stationList);

		//adds passenger object to queue
		trainStorage.add(passenger);
	}

}//end Train class
