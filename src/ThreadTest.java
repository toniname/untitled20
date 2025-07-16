public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread printThread = new Thread(() -> {
            System.out.println("Exec tread");

            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait(); // wait for notification
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Notify");
        });

        printThread.start();
        Thread.sleep(3000);

        synchronized (printThread) {
            printThread.notify(); // wake up the thread
        }
    }
}
