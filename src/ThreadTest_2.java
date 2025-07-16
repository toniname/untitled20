public class ThreadTest_2 {

    public static void main(String[] args) throws InterruptedException {

        Thread endlessTread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()){
                    System.out.println("Running...");
                }
            }
        };
        endlessTread.start();
        Thread.sleep(1000);
        endlessTread.interrupt();

        System.out.println(Thread.currentThread().getPriority());



    }
}
