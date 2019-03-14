/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {

    private WaitingArea waitingArea;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        // TODO Implement required functionality
        this.waitingArea = waitingArea;


    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the waiting area
     */
    @Override
    public void run() {
        // TODO Implement required functionality
        
        while (SushiBar.isOpen){
            Customer customer = new Customer();

            waitingArea.enter(customer);

            try{
                Thread.sleep(SushiBar.doorWait);
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }

        }
        SushiBar.write("**** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
        
    }

    

    // Add more methods as you see fit
}
