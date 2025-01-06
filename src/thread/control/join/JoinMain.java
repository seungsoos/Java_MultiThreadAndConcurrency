package thread.control.join;

import util.MyLogger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMain {

    public static void main(String[] args) throws InterruptedException {

        SumTask sumTask1 = new SumTask(1, 50);
        SumTask sumTask2 = new SumTask(51, 100);
        Thread thread1 = new Thread(sumTask1, "Thread-1");
        Thread thread2 = new Thread(sumTask2, "Thread-2");

        thread1.start();
        thread2.start();

        log("join ()- Main 스레드가 Thread 1,2 대기 시작");
        // Main 스레드의 무기한 대기
        thread1.join();
        thread2.join();
        log("join ()- Main 스레드가 Thread 1,2 대기 끝");

        log("task1.result = "+ sumTask1.result);
        log("task2.result = "+ sumTask2.result);

        log("task sum = " + (sumTask1.result+sumTask2.result));
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 끝 result =" + result);
        }
    }
}
