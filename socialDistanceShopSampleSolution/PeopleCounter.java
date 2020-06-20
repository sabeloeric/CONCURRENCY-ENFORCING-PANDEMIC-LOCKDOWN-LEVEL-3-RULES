package socialDistanceShopSampleSolution;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.*;


//class to keep track of people inside and outside and left shop
public class PeopleCounter {
	private AtomicInteger peopleOutSide; //counter for people arrived but not yet in the building
	private AtomicInteger peopleInside; //people inside the shop
	private AtomicInteger peopleLeft; //people left the shop
	private AtomicInteger maxPeople; //maximum for lockdown rules
	
	Semaphore maxAllowed;

	PeopleCounter(int max) {
		peopleOutSide = new AtomicInteger(0);
		peopleInside = new AtomicInteger(0);
		peopleLeft = new AtomicInteger(0);
		maxPeople = new AtomicInteger(max);

		maxAllowed = new Semaphore(max);
	}
		
	//getter
	public int getWaiting() {
		return peopleOutSide.get();
	}

	//getter
	public int getInside() {
		return peopleInside.get();
	}
	
	//getter
	public int getTotal() {
		return (peopleOutSide.get()+peopleInside.get()+peopleLeft.get());
	}

	//getter
	public int getLeft() {
		return peopleLeft.get();
	}
	
	//getter
	public int getMax() {
		return maxPeople.get();
	}
	
	//getter
	public void personArrived() {
		peopleOutSide.incrementAndGet();
	}
	
	//update counters for a person entering the shop
	public void personEntered() throws InterruptedException{
		maxAllowed.acquire();
		peopleOutSide.decrementAndGet();
		peopleInside.incrementAndGet();
	}

	//update counters for a person exiting the shop
	public void personLeft() {
		maxAllowed.release();
		peopleInside.decrementAndGet();
		peopleLeft.incrementAndGet();
		
	}

	//reset - not really used
	public void resetScore() {
		peopleInside.set(0);
		peopleOutSide.set(0);
		peopleLeft.set(0);
	}
}
