import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorExample {
    static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        // Create a thread pool with 4 threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        };

        for (int i = 0; i < 3; i++) {
            executor.execute(task);
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdownNow();
        System.out.println("Final Count: " + count);









        /*ReentrantLock lock = new ReentrantLock();

        Runnable task = () -> {
            lock.lock();
        try {
            count.getAndIncrement();

        }finally {
            lock.unlock();
        }
        };*/


    }
}
