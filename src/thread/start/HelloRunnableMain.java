package thread.start;

public class HelloRunnableMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        System.out.println(Thread.currentThread().getName() + ": start 호출 전");

        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread = new Thread(helloRunnable);
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": start 호출 후");
        System.out.println(Thread.currentThread().getName() + ": main() end");

    }
}
