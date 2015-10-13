package task3;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageBus {

    Logger log = Logger.getLogger(getClass());
    private Map<MessageTheme, LinkedList<String>> messages;
    AtomicInteger counter = new AtomicInteger(0);

    public MessageBus() {
        this.messages = new HashMap<MessageTheme, LinkedList<String>>();
        for (int i = 0; i < MessageTheme.values().length; i++) {
            this.messages.put(MessageTheme.values()[i], new LinkedList<String>());
        }
    }

    public synchronized String getMessage(MessageTheme theme) {
        String returnedMessage = messages.get(theme).getFirst();
        messages.get(theme).removeFirst();
        return returnedMessage;
    }

    public synchronized void setMessage(String randomMessage, MessageTheme theme) {
        log.info("Get message with theme:" + theme + " Message count: " + counter.incrementAndGet());
        messages.get(theme).addLast(randomMessage);
    }
}
