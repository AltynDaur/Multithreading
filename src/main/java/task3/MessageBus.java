package task3;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class MessageBus {

    Logger log = Logger.getLogger(getClass());
    private Map<MessageTheme, String> messages;

    public MessageBus() {
        this.messages = new HashMap<MessageTheme, String>();
    }

    public synchronized String getMessage(MessageTheme theme) {
        if(messages.containsKey(theme)){
            return messages.get(theme);
        } else {
            throw new IllegalArgumentException("There is no message with theme: " + theme);
        }
    }

    public synchronized void setMessage(String randomMessage, MessageTheme theme) {
        log.info("Get message with theme:" + theme);
        messages.put(theme,randomMessage);
    }
}
