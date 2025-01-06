package util;

import static util.MyLogger.log;

public abstract class ThreadUtils {

    public  static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log("InterruptedException");
            throw new RuntimeException();
        }
    }
}
