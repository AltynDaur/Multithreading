package task1;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static final long MAX_DISTANCE = 10000;
    private static AtomicInteger finishNumber = new AtomicInteger();
    private static AtomicBoolean isDisqualified = new AtomicBoolean(false);
    private final long ownTime = System.currentTimeMillis();
    Logger log = Logger.getLogger(getClass());
    private long friction;
    private long distance;
    private String name;
    public Car(String name, long friction) {
        this.name = name;
        this.friction = 100;
    }

    public void run() {
        try {
            while (distance < MAX_DISTANCE) {
                Thread.sleep(friction);
                distance += 100;
                log.info(name + " " + distance);
                if(!isDisqualified.get()){
                    if(System.currentTimeMillis() - ownTime >= 5000){
                        log.info(name + " is disqualified! HAHAHA!");
                        Thread.currentThread().interrupt();
                        isDisqualified.set(true);
                    }
                }
            }
            finishNumber.getAndIncrement();
            if(finishNumber.get() == 1){
                log.info("Winner is " + name);
            } else {
                log.info(name + " lost!");
            }
        } catch (InterruptedException e) {
            log.error(e);
        }
    }
}
