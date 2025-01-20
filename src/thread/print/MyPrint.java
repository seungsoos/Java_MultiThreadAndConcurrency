package thread.print;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrint {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q) :");
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt();
                break;
            }
            printer.addJob(input);
        }


    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {

            while (!Thread.interrupted()) {
                if (jobQueue.isEmpty()) {
                    Thread.yield();
                    continue;
                }
                try {
                    String job = jobQueue.poll();
                    log("출력 시작 : " + job + ", 대기문서 : " + jobQueue);
                    log("출력 완료");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log("인터럽트 예외 발생!");
                    break;
                }
            }
            log("프린터 출력 종료");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }

    }
}
