public class Counter {

    static int count = 0;

    public synchronized static void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                increment();
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


        System.out.println("Count: " + count);
    }


}
