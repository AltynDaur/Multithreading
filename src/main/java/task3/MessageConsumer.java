package task3;

import org.apache.log4j.Logger;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageConsumer implements Runnable{

    private MessageBus messageBus;
    private MessageTheme messageTheme;
    Logger log = Logger.getLogger(getClass());

    public MessageConsumer(MessageBus messageBus, MessageTheme messageTheme) {
        this.messageBus = messageBus;
        this.messageTheme = messageTheme;
    }

    public void run() {
        while (true){
            try{
                String message = messageBus.getMessage(messageTheme);
                log.info(Thread.currentThread().getName() + " theme: " + messageTheme +" message: " + message);
            } catch (IllegalArgumentException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
