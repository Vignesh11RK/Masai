import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        System.out.println("hello");
//        System.out.println("The program is running " + Thread.currentThread().getName() + " Thread");
//
//        // Lambda replacing the MyThread class
//        Runnable task = () -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(i + " from " + Thread.currentThread().getName() + " Thread");
//                try {
//                    Thread.sleep(1000); // 1 second sleep
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };

//        Thread myThread = new Thread(task);
//        myThread.setName("My thread");
//        myThread.start();

        // Main thread loop
//        for (int i = 0; i < 100; i++) {
//            System.out.println(i + " from " + Thread.currentThread().getName() + " Thread");
//            try {
//                Thread.sleep(100); // 100 ms sleep
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        ExecutorService executor= Executors.newFixedThreadPool(3);
//        executor.execute(
//                () ->{
//                    for(int i=0;i<=5;i++){
//                        System.out.println(i+"from"+Thread.currentThread().getName());
//                        try{
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//        );


        Callable<String> task=() ->{
            Thread.sleep(500);
            return "Hello"+Thread.currentThread().getName();
        };

        System.out.println("Before calling executor task");
        Future<String> result=executor.submit(task);
        System.out.println("After calling executor task");

        try {
            System.out.println("Waiting for the result");
            System.out.println("Result from future object "+result.get());
            System.out.println("After getting result .. Done ");
        }

        catch (ExecutionException | InterruptedException e){
            System.out.println("Something went wrong");
        }



    }
}
