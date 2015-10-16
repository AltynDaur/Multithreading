package task3;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageProducer implements Runnable {

    private MessageBus messageBus;
    Logger log = Logger.getLogger(getClass());

    public MessageProducer(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            MessageTheme theme = MessageTheme.values()[new Random().nextInt(MessageTheme.values().length)];
            try {
                messageBus.putMessage(createRandomMessage(), theme);
            } catch (InterruptedException e) {
                log.info("Thread "+ Thread.currentThread().getName() + "interrupted");
            }
        }
        log.info("I'm finished: Producer " + Thread.currentThread().getName());
    }

    private String createRandomMessage(){
        return "Some cool message " + Thread.currentThread().getName();
    }
}
