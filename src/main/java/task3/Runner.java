package task3;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class Runner {
    public static void main(String[] args) {
        MessageBus messageBus = new MessageBus();
        for (int i = 0; i < 10; i++) {
            new Thread(new MessageProducer(messageBus)).start();
        }

        for (int i = 0; i < MessageTheme.values().length; i++) {
            new Thread(new MessageConsumer(messageBus, MessageTheme.values()[i])).start();
        }
    }
}
