package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");

        log("Main 시작");
        thread.start();

        log("runFlag 상태 확인 = " + myTask.runFlag);
        sleep(1000);
        log("runFlag False로 변경시도");
        myTask.runFlag = false;
        log("runFlag 상태 확인 = " + myTask.runFlag);
        log("Main 끝");
    }

    public static class MyTask implements Runnable {

//        boolean runFlag = true;
        volatile boolean runFlag = true;


        @Override
        public void run() {

            log("작업 시작");
            while (runFlag) {
            }
            log("작업 끝");

        }
    }
}
