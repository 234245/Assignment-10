class NamedThread implements Runnable {
    public void run() {
        System.out.println("Running thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new NamedThread(), "Thread-Rohim");
        Thread thread2 = new Thread(new NamedThread(), "Thread-Kamal");
        Thread thread3 = new Thread(new NamedThread(), "Thread-Jamal");

        // Starting the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
