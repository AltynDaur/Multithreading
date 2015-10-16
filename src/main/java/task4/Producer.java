package task4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dauren_Altynbekov on 10/16/2015.
 */
public class Producer implements Runnable {
    SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        resource.increment();
    }
}
