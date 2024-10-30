class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}

class CounterThread implements Runnable {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 1500; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) {
        Counter sharedCounter = new Counter();

        // Creating multiple threads that share the same counter
        Thread thread1 = new Thread(new CounterThread(sharedCounter));
        Thread thread2 = new Thread(new CounterThread(sharedCounter));
        Thread thread3 = new Thread(new CounterThread(sharedCounter));
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final count: " + sharedCounter.getCount());
    }
}
