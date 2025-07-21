import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExample {

    // Створюємо потокобезпечний лічильник (без synchronized)
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        // === 1. Створення пулу потоків вручну ===
        // corePoolSize: мінімальна кількість потоків, які завжди активні
        int corePoolSize = 5;

        // maxPoolSize: максимальна кількість потоків у пулі
        int maximumPoolSize = 10;

        // keepAliveTime: скільки "зайві" потоки (понад corePoolSize) можуть бути неактивними перед знищенням
        long keepAliveTime = 60;

        // Черга задач: всі задачі, що не помістились у активні потоки, йдуть сюди
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

        // === 2. Створення самого ThreadPoolExecutor ===
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                taskQueue
        );


        // === 3. Відправляємо 100 задач до пулу ===
        // Кожна задача виконує 1000 збільшень лічильника

        int tasksNumber = 100;
        for (int i = 0; i < tasksNumber; ++i) {
            executor.execute(() -> {
                for (int j = 0; j < 1000; ++j) {
                    counter.incrementAndGet(); // БЕЗ synchronized!
                }
            });
        }


        // === 4. Завершення виконання ===

        // Ми більше не надсилаємо нові задачі
        executor.shutdown();

        // Чекаємо завершення всіх задач до 1 хвилини
        boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (finished){
            System.out.println("Всі задачі виконано");
        } else{
            System.err.println("Не вийшло закінчити за 1 хвилину");
        }
        // Виводимо фінальний результат — очікуємо 100 задач × 1000 інкрементів = 100_000
        System.out.println(counter.get());
    }


}
