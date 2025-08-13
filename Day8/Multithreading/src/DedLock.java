import java.util.concurrent.locks.ReentrantLock;

public class DedLock {

    // Two locks that will cause the deadlock

    private static final ReentrantLock lock1 = new ReentrantLock();

    private static final ReentrantLock lock2 = new ReentrantLock();


    // First method: gets lock1 first, then lock2

    public static void method1() {

        lock1.lock();

        System.out.println(Thread.currentThread().getName() + " got lock1");

        try {

            // Wait a bit to make deadlock more likely

            Thread.sleep(50);

            System.out.println(Thread.currentThread().getName() + " trying to get lock2...");

            lock2.lock(); // This will cause deadlock!

            try {

                System.out.println(Thread.currentThread().getName() + " got both locks!");

            } finally {

                lock2.unlock();

            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {

            lock1.unlock();

        }

    }



    // Second method: gets lock2 first, then lock1 (OPPOSITE ORDER!)

    public static void method2() {

        lock2.lock();

        System.out.println(Thread.currentThread().getName() + " got lock2");



        try {

            // Wait a bit to make deadlock more likely

            Thread.sleep(50);

            System.out.println(Thread.currentThread().getName() + " trying to get lock1...");

            lock1.lock(); // This will cause deadlock!

            try {

                System.out.println(Thread.currentThread().getName() + " got both locks!");

            } finally {

                lock1.unlock();

            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {

            lock2.unlock();

        }

    }



    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting deadlock example...");

        System.out.println("This program will get stuck (deadlock)!");



        Thread thread1 = new Thread(() -> method1(), "Thread-1");

        Thread thread2 = new Thread(() -> method2(), "Thread-2");



        thread1.start();

        thread2.start();



        // Wait 3 seconds to see the deadlock

        Thread.sleep(3000);

        System.out.println("DEADLOCK DETECTED! Program is stuck.");

        System.out.println("You'll need to stop the program manually.");



        // In a real program, you'd handle this better

        System.exit(0);

    }

}

