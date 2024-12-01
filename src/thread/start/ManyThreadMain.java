package thread.start;

import static util.MyLogger.log;

public class ManyThreadMain {

    public static void main(String[] args) {
        log("main start");

        HelloRunnable helloRunnable = new HelloRunnable();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(helloRunnable);
            thread.start();
        }

        log("main end");
    }
}