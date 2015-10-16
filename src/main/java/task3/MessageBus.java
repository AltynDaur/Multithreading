package task3;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageBus {

    Logger log = Logger.getLogger(getClass());
    private Map<MessageTheme, LinkedList<String>> messages;
    private Map<MessageTheme, AtomicInteger> messagesCounters;
    AtomicInteger messageCounter = new AtomicInteger(0);
    private Lock takingLock = new ReentrantLock();
    private Lock puttingLock = new ReentrantLock();
    private Condition notEmpty = takingLock.newCondition();
    private Condition notFull = puttingLock.newCondition();
    private final int capacity = Integer.MAX_VALUE;

    public MessageBus() {
        this.messages = new HashMap<MessageTheme, LinkedList<String>>();
        this.messagesCounters = new HashMap<MessageTheme, AtomicInteger>();
        for (int i = 0; i < MessageTheme.values().length; i++) {
            this.messages.put(MessageTheme.values()[i], new LinkedList<String>());
            this.messagesCounters.put(MessageTheme.values()[i], new AtomicInteger(0));
        }
    }

    public String takeMessage(MessageTheme theme) throws InterruptedException {
        String returnedMessage;
        takingLock.lockInterruptibly();
        try {
            while (messagesCounters.get(theme).get() == 0){
                notEmpty.await();
            }
            returnedMessage = messages.get(theme).getFirst();
            messages.get(theme).removeFirst();
            int c = messagesCounters.get(theme).decrementAndGet();
            if (c > 0)
                notEmpty.signal();
        } finally {
            takingLock.unlock();
        }
        return returnedMessage;
    }

    public void putMessage(String randomMessage, MessageTheme theme) throws InterruptedException {
        log.info("Get message with theme:" + theme + " Message count: " + messageCounter.incrementAndGet());
        puttingLock.lockInterruptibly();
        try {
            while (messagesCounters.get(theme).get() == capacity) {
                notFull.await();
            }
            messages.get(theme).addLast(randomMessage);
            int c = messagesCounters.get(theme).incrementAndGet();
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            puttingLock.unlock();
        }
        messages.get(theme).addLast(randomMessage);
    }
}
