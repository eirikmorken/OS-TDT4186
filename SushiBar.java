import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
private static AtomicInteger at = new AtomicInteger(0);
https://examples.javacodegeeks.com/core-java/util/concurrent/atomic/atomicinteger/java-atomicinteger-example/
*/

public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 15;
    private static int waitressCount = 8;
    private static int duration = 4;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 2000; // Used to calculate the time the customer spends eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        // TODO initialize the bar and start the different threads

        Clock clock = new Clock(duration);

        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);

        Door door = new Door(waitingArea);
        (new Thread(door)).start();
        ArrayList<Thread> threads = new ArrayList();
        for(int i = 0; i<waitressCount; i++){
            Waitress waitress = new Waitress(waitingArea);
            Thread thread = new Thread(waitress);
            thread.start();
            threads.add(thread);
        }
        for(Thread t: threads){
            try{
                t.join();
            }catch (InterruptedException ie) {}
            
        }
        int totalOrders = servedOrders.get() + takeawayOrders.get();
        SushiBar.write("Total orders: " + totalOrders);
        SushiBar.write("Number of takeaway:" + takeawayOrders.get());
        SushiBar.write("Number of served orders: " + servedOrders.get());

    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
