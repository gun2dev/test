package gun2.dev.glovesquest.utils;

import java.util.concurrent.ThreadLocalRandom;

public class ChanceManager {

    public static boolean percent(double percent){
        return percent > ThreadLocalRandom.current().nextInt( 10000);
    }

    public static int getNum(int size){
        return ThreadLocalRandom.current().nextInt(0, size);
    }
}
