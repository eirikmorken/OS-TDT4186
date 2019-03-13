import java.util.ArrayList;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {  
    
    public ArrayList<Customer> waiting_que;
    public int size;

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */

    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.waiting_que = new ArrayList();
        this.size = size;

    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        if(this.waiting_que.size()>= this.size){
            try{
                this.wait();
            }
            catch (Exception e){
                e.printStackTrace();;
                //throw new InterruptedException();
            }
            
        }
        this.waiting_que.add(customer);
        this.notifyAll();
        // TODO Implement required functionality
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        if(this.waiting_que.isEmpty()){
            try{
                this.wait();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        Customer customer = this.waiting_que.get(0);
        this.waiting_que.remove(0);
        this.notifyAll();
        return customer;

        // TODO Implement required functionality
    }

    // Add more methods as you see fit
}
