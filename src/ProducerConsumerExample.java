import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(item);
        System.out.println("Produced: " + item);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        int item = buffer.poll();
        System.out.println("Consumed: " + item);
        notifyAll();
        return item;
    }
}

class Producer implements Runnable {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(100);  // simulate production time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                buffer.consume();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(10);

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
