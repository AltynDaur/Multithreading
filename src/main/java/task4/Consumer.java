package task4;

/**
 * Created by Dauren_Altynbekov on 10/16/2015.
 */
public class Consumer implements Runnable {
    SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        resource.decrement();
    }
}
