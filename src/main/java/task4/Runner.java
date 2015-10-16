package task4;

/**
 * Created by Dauren_Altynbekov on 10/16/2015.
 */
public class Runner {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
        new Thread(consumer).start();
        new Thread(producer).start();
    }
}
