package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV4 {

//    public static final int TASK_SIZE = 1100; // 일반
//    public static final int TASK_SIZE = 1200; // 긴급
    public static final int TASK_SIZE = 1201; // 거절

    public static void main(String[] args) {

        // Queue를 무한대로 사용시 MaximumPoolSize로 증가하지않으니 주의 필요!!
        // new LinkedBlockingQueue(); <- 이런식으로 설정시 무한대
        ExecutorService es = new ThreadPoolExecutor
                (100, 200, 60,
                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000));

        log("=== Pool 생성 ===");
        printState(es);

        long startMs = System.currentTimeMillis();

        for (int i = 0; i < TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(e);
            }
        }
        es.close();
        long endMs = System.currentTimeMillis();
        log("time : " + (endMs - startMs));
    }

}
