public class MyThreadv2 {

    public static void main(String[] args) {

        System.out.println("hello");
        System.out.println("The program is running " + Thread.currentThread().getName() + " Thread");

        // Lambda expression for Runnable
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " from " + Thread.currentThread().getName() + " Thread");
                try {
                    Thread.sleep(1000); // 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("My thread");
        thread.start();

        // Main thread loop
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " from " + Thread.currentThread().getName() + " Thread");
            try {
                Thread.sleep(100); // 100 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
