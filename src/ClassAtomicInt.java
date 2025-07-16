import java.util.concurrent.atomic.AtomicInteger;

public class ClassAtomicInt {


    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                atomicInteger.incrementAndGet(); //atomicInteger.addAndGet(1);
            }
        };

        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Initial Value: " + atomicInteger.get());
    }

}
