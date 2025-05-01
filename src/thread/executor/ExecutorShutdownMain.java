package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("longTask", 100_000)); //100초 대기하는 이상 작업

        printState(es);
        log("== Shutdown 시작");
        shutdownAndAwaitTermination(es);
        printState(es);
        log("== Shutdown 종료");
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {
        // non-blocking, 새로운 작업을 하지않는다, 처리중이거나 큐에 이미 대기중인 작업은 처리한다. 이후에 품의 스레드 종료
        es.shutdown();

        try {
            // Shutdown시 까지 10초 대기하였지만, 종료되지않음
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                // 정상 종료가 너무 오래걸리면
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow();
                // 작업이 종료될때까지 대기
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination() 으로 대기중인 현재 스레드가 인터럽트 될 수 있다.
            es.shutdownNow();
            throw new RuntimeException(e);
        }

    }
}
