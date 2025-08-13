import java.util.concurrent.locks.ReentrantLock;

public class BasicReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();

    private int counter = 0;

    public void increment() {

        lock.lock(); // Acquire the lock

        try {

            counter++; // Critical section - only one thread can execute this

            System.out.println(Thread.currentThread().getName() +

                    " incremented counter to: " + counter);

        } finally {

            lock.unlock(); // Always unlock in finally block!

        }
    }

    public int getCounter() {

        lock.lock();

        try {

            return counter;

        } finally {

            lock.unlock();

        }

    }



    public static void main(String[] args) throws InterruptedException {

        BasicReentrantLockExample example = new BasicReentrantLockExample();



        // Create 3 threads that will increment the counter

        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {

            threads[i] = new Thread(() -> {

                for (int j = 0; j < 2; j++) {

                    example.increment();

                    try {

                        Thread.sleep(100); // Small delay

                    } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                    }

                }

            }, "Thread-" + i);

            threads[i].start();

        }



        // Wait for all threads to finish

        for (Thread thread : threads) {

            thread.join();

        }

        System.out.println("Final counter value: " + example.getCounter());

    }

}