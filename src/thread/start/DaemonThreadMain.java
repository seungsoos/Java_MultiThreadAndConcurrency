package thread.start;

public class DaemonThreadMain {


    /**
     * 사용자 스레드가 모두 종료되어야 JVM이 종료되지만,
     * 데몬 스레드의 경우 JVM이 기다려주지않고, 시용자 스레드가 모두 종료되면 JVM은 종료된다.
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // 데몬 스레드 여부 지정
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DaemonThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run() start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ": run() end");
        }
    }
}
