package task3;

import java.util.Random;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageProducer implements Runnable {

    private MessageBus messageBus;

    public MessageProducer(MessageBus messageBus) {
        this.messageBus = messageBus;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            MessageTheme theme = MessageTheme.values()[new Random(MessageTheme.values().length).nextInt() - 1];
            messageBus.setMessage(createRandomMessage(), theme);
        }
    }

    private String createRandomMessage(){
        return "Some cool message " + Thread.currentThread().getName();
    }
}
