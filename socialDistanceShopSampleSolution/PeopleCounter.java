package socialDistanceShopSampleSolution;

import java.util.concurrent.Semaphore;


//class to keep track of people inside and outside and left shop
public class PeopleCounter {
	private int peopleOutSide; //counter for people arrived but not yet in the building
	private int peopleInside; //people inside the shop
	private int peopleLeft; //people left the shop
	private int maxPeople; //maximum for lockdown rules
	
	Semaphore maxAllowed;

	PeopleCounter(int max) {
		peopleOutSide = 0;
		peopleInside = 0;
		peopleLeft = 0;
		maxPeople = max;

		maxAllowed = new Semaphore(max);
	}
		
	//getter
	public int getWaiting() {
		return peopleOutSide;
	}

	//getter
	public int getInside() {
		return peopleInside;
	}
	
	//getter
	synchronized public int getTotal() {
		return (peopleOutSide+peopleInside+peopleLeft);
	}

	//getter
	public int getLeft() {
		return peopleLeft;
	}
	
	//getter
	public int getMax() {
		return maxPeople;
	}
	
	//getter
	public void personArrived() {
		peopleOutSide++;
	}
	
	//update counters for a person entering the shop
	public void personEntered() throws InterruptedException{
		maxAllowed.acquire();
		peopleOutSide--;
		peopleInside++;
	}

	//update counters for a person exiting the shop
	public void personLeft() {
		maxAllowed.release();
		peopleInside--;
		peopleLeft++;
		
	}

	//reset - not really used
	public void resetScore() {
		peopleInside = 0;
		peopleOutSide = 0;
		peopleLeft = 0;
	}
}
