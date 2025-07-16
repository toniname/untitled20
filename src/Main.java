//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*HelloThread thread = new HelloThread();
        //thread.run();
        thread.start();
        System.out.println("Main thread");*/

        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println( "Hello from thread_2!");
            }
        });

        thread.start();
        System.out.println("Main thread_1");

        Thread thread1 = new Thread(() -> System.out.println("Hello from lambda thread_01!"));
        thread1.start();
        System.out.println("Main thread_02");*/

        new HelloThread().start();

        System.out.println("Main thread_1");
        new Thread(() -> System.out.println("Hello from lambda thread_01!")).start();
        System.out.println("Main thread_02");


    }
}