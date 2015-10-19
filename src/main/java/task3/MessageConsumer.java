package task3;

import org.apache.log4j.Logger;

import java.util.NoSuchElementException;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageConsumer implements Runnable {

    private MessageBus messageBus;
    private MessageTheme messageTheme;
    Logger log = Logger.getLogger(getClass());

    public MessageConsumer(MessageBus messageBus, MessageTheme messageTheme) {
        this.messageBus = messageBus;
        this.messageTheme = messageTheme;
    }

    public void run() {
        while (messageBus.hasMessages(messageTheme)) {
            try {
                String message = messageBus.takeMessage(messageTheme);
                log.info(Thread.currentThread().getName() + " theme: " + messageTheme + " message: " + message);
            } catch (InterruptedException e) {
                log.info("Thread " + Thread.currentThread().getName() + "interrupted");
            }
        }
        log.info("I'm finished! Consumer " + Thread.currentThread().getName());
    }
}
