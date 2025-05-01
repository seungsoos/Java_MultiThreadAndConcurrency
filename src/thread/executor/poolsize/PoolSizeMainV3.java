package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV3 {

    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        /**
         * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         * 60L, TimeUnit.SECONDS,
         * new SynchronousQueue<Runnable>());
         */
        log("=== Pool 생성 ===");
        printState(es);

        for (int i = 0; i < 100; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es);
        }
        es.close();
    }

}
