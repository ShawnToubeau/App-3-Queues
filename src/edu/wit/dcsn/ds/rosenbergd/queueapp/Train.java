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

	public Train(String direction, int position, int maxCapacity, ArrayList<Integer> stationLocationList)
    {

		this.trainID = counter++;
		this.position = position;
		this.maxCapacity = maxCapacity;

		//sets initial direction
		if (direction.equals("inbound"))
		{
		    //direction set to inbound
			this.inbound = true;
		}//end if
		else
        {
            //direction set to outbound
			this.inbound = false;
		}//end else

        //adds random number of initial passengers
		for (int i = 0; i < randomNum; i++)
		{
			addPassengers(stationLocationList);
		}//end for
	}//end train constructor

    //returns train position
	public int getPosition() {
		return this.position;
	}

	//returns train ID
	public int getTrainID() {
		return this.trainID;
	}

	//returns train direction
	public boolean getDirection() {
		return inbound;
	}

	//returns train max capacity
	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	//returns current station that the train is at
	public int getCurrentStation() throws NullPointerException{
		try
        {
			return this.currentStation.stationID;
		}//end try
		catch (NullPointerException ex)
        {
			return -1;
		}//end catch
	}

	//moves the train one position
    //arguments: train route
	public void moveTrain(TrainRoute trainRoute)
    {
        //clears current station
		resetCurrentStation();

		//if train route style is circular
		if (trainRoute.getStyle().equals("circular")) {
			if (this.inbound)
			{
				if (this.position == trainRoute.length)
				{
					this.position = 0;
				}//end if
				else
                {
					this.position++;
				}//end else
			}//end if
			else if (!this.inbound)
			{
				if (this.position == 0)
				{
					this.position = 20;
				}//end if
				else
				{
					this.position--;
				}//end else
			}//end else if

		}//end if
        //if the train route is linear
		else if (trainRoute.getStyle().equals("linear"))
		{
			if (this.inbound)
			{
				if (this.position == trainRoute.length)
				{
					this.inbound = false;
					this.position--;
				}//end if
				else
                {
					this.position++;
				}//end else
			}//end if
			else if (!this.inbound)
			{//end else if
				if (this.position == 0)
				{
					this.inbound = true;
					this.position++;
				}//end if
				else
                {
					this.position--;
				}//end else
			}
		}//end else if
	}//end moveTrain

    //checks if train is at a station
    //arguments: list of all the stations
	public void checkIfAtStation(ArrayList<Station> stationList) {
	    //iterates through all the stations
		for (int i = 0; i < stationList.size(); i++)
		{
		    //if the train position equals a station ID(position)
			if (this.position == stationList.get(i).stationID)
			{
			    //train is at matching station
				this.currentStation = stationList.get(i);
			}//end if
		}//end for
	}

	//clears currentStation
	public void resetCurrentStation() {
		this.currentStation = null;
	}

	//removes passengers from the train
	public ArrayList<Integer> disembark(){
	    //arraylist for keeping track of removed passengers
        ArrayList<Integer> removedPassengerIDs = new ArrayList<>();
        Passenger passengerCopy = null;
        //iterates through all the passengers in the train
		for (int i = 0; i < this.trainStorage.size(); i++)
		{
		    //checks if a passenger's destination matches the current station
			if (this.trainStorage.get(i).getDestination() == this.currentStation.getStationID())
			{
			    //copy the passenger
			    passengerCopy = this.trainStorage.get(i);
			    //remove passenger from the train
				this.trainStorage.remove(passengerCopy);
				//add removed passenger ID to arraylist
				removedPassengerIDs.add(passengerCopy.getPassengerID());
			}//end if
		}//end for
        //return arraylist
        return removedPassengerIDs;
	}//end disembark

    //adds passengers to the train
	public ArrayList<Integer> embark(){
	    //arraylist used for keeping track of added passengers
	    ArrayList<Integer> addedPassengerIDs = new ArrayList<>();
	    Passenger passengerCopy = null;
	    //while the train has space and the current station has passengers
		while (this.trainStorage.size() < this.maxCapacity && this.currentStation.hasQueuedPassengers())
        {
            //create copy of passenger
		    passengerCopy = this.currentStation.nextPassenger();
		    //add passenger to train
			this.trainStorage.add(passengerCopy);
			//add added passenger ID to arraylist
			addedPassengerIDs.add(passengerCopy.getPassengerID());
		}//end while
        //return arraylist
		return addedPassengerIDs;
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
