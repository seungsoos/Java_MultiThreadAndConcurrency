package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        System.out.println(Thread.currentThread().getName() + ": start 호출 전");

        HelloThread helloThread = new HelloThread();
        helloThread.start();

        System.out.println(Thread.currentThread().getName() + ": start 호출 후");
        System.out.println(Thread.currentThread().getName() + ": main() end");

    }
}
