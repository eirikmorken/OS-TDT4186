import java.util.concurrent.ThreadLocalRandom;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {

    int customerID;
    int num_orders;
    int num_takeaway;
    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        this.customerID = SushiBar.customerCounter.get();
        SushiBar.customerCounter.increment();
        // TODO Implement required functionality

    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        // TODO Implement required functionality
        this.num_orders = ThreadLocalRandom.current().nextInt(1, SushiBar.maxOrder);
        this.num_takeaway = ThreadLocalRandom.current().nextInt(1, SushiBar.maxOrder +1 - this.num_orders);
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        // TODO Implement required functionality
        return this.customerID;
    }

 

    /**
     * @return the num_orders
     */
    public int getNum_orders() {
        return num_orders;
    }

    /**
     * @return the num_takeaway
     */
    public int getNum_takeaway() {
        return num_takeaway;
    }


    // Add more methods as you see fit
}
