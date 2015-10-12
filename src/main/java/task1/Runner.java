package task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dauren_Altynbekov on 10/12/2015.
 */
public class Runner {
    public static void main(String[] args) {
        List<Car> race = new ArrayList<Car>();
        for (int i = 0; i < 10; i++) {
            race.add(new Car("car"+i, 100));
        }
        for (int i = 0; i < 10; i++) {
            new Thread(race.get(i)).start();
        }
    }
}
