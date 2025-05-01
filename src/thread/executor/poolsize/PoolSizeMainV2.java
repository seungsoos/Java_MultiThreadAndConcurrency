package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV2 {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        /**
         * new ThreadPoolExecutor(nThreads, nThreads,
         * 0L, TimeUnit.MILLISECONDS,
         * new LinkedBlockingQueue<Runnable>());
         */

        log("=== Pool 생성 ===");
        printState(es);

        for (int i = 0; i < 5; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es);
        }
        es.close();
    }

}
