package socialDistanceShopSampleSolution;
import java.util.concurrent.Semaphore;
// GridBlock class to represent a block in the shop.

public class GridBlock {
	private boolean isOccupied;
	private final boolean isExit; 
	private final boolean isCheckoutCounter;
	private int [] coords; // the coordinate of the block.
	private int ID;
	
	public static int classCounter=0;

	// allow one customer to accupy block
	Semaphore available; 
	
	GridBlock(boolean exitBlock, boolean checkoutBlock) throws InterruptedException {
		isExit=exitBlock;
		isCheckoutCounter=checkoutBlock;
		isOccupied= false;
		ID=classCounter;
		classCounter++;

		available = new Semaphore(1);
	}
	
	GridBlock(int x, int y, boolean exitBlock, boolean refreshBlock) throws InterruptedException {
		this(exitBlock,refreshBlock);
		coords = new int [] {x,y};
	}
	
	//getter
	public  int getX() {return coords[0];}  
	
	//getter
	public  int getY() {return coords[1];}
	
	//for customer to move to a block
	public boolean get() throws InterruptedException {
		if(isOccupied){
			return false;
		}
		// lock if accupied
		available.acquire();
		isOccupied=true;
		return true;
	}
		
	//for customer to leave a block
	public  void release() {
		// release once not accupied
		isOccupied =false;
		available.release();
	}
	
	//getter
	public boolean occupied() {
		return isOccupied;
	}
	
	//getter
	public  boolean isExit() {
		return isExit;	
	}

	//getter
	public  boolean isCheckoutCounter() {
		return isCheckoutCounter;
	}
	
	//getter
	public int getID() {return this.ID;}
}
