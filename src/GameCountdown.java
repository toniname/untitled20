import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameCountdown {

    public static void main(String[] args) throws InterruptedException {

        // === 1. Створюємо планувальник на 1 потік ===
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // === 2. Кількість секунд для відліку ===
        final int[] secondsToCountDown = {5};

        // === 3. Задача, яка виконується щосекунди ===
        Runnable cooldownTask = () -> {
            if (secondsToCountDown[0] > 0) {
                System.out.println("⏳ Старт через: " + secondsToCountDown[0] + " сек...");
                Toolkit.getDefaultToolkit().beep();
                secondsToCountDown[0]--;
            } else {
                System.out.println("Game start in ");
                Toolkit.getDefaultToolkit().beep();
                executor.shutdown();
            }
        };
        System.out.println("🟢 Підготовка до старту гри...");
        // Виводимо кількість секунд до закінчення відліку
        // === 4. Запускаємо задачу кожну секунду без початкової затримки ===
        executor.scheduleAtFixedRate(cooldownTask, 0, 1, TimeUnit.SECONDS);

        // === 5. Очікуємо завершення відліку, щоб не завершити програму раніше часу ===
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("🏁 Всі підготовки завершено.");

    }

}
