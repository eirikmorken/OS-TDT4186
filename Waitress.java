/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    private WaitingArea waitingArea;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        
        while(SushiBar.isOpen || (!waitingArea.queue_empty())){

            Customer customer;
            synchronized(waitingArea){
                customer = waitingArea.next();
                
            }
            try{
                SushiBar.write("Customer: " + customer.getCustomerID() + " is now fetched");
                Thread.sleep(SushiBar.waitressWait);             
            }catch(InterruptedException ie){

            }
            customer.order();
            SushiBar.write("Customer: "+ customer.getCustomerID() + " is now ordering");
            SushiBar.servedOrders.add(customer.getNum_orders());
            SushiBar.takeawayOrders.add(customer.getNum_takeaway());
            
            try{
                SushiBar.write("Customer: " + customer.getCustomerID() + " is now eating");
                Thread.sleep(SushiBar.customerWait);             
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
            SushiBar.write("Customer: " + customer.getCustomerID() + " is now leaving");
            
        }

    }

}

