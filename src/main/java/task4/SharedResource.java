package task4;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dauren_Altynbekov on 10/16/2015.
 */
public class SharedResource {
    public AtomicInteger counter = new AtomicInteger(10);
    private Lock incrementLock = new ReentrantLock();
    private Lock decrementLock = new ReentrantLock();
    Logger log = Logger.getLogger(getClass());

    public void increment() {
        while (counter.get() < 10){
            counter.incrementAndGet();
            log.info("Counter increased: " + counter.get());
        }
    }


    public void decrement() {
        while (counter.get() > 5){
            counter.decrementAndGet();
            log.info("Counter decresead: " + counter.get());
        }
    }
}
