import com.sun.source.tree.WhileLoopTree;

public class SharedResource {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while(available) wait();
        data = value;
        available = true;
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (!available) wait();
        available = false;
        notify();
        return data;
    }
}