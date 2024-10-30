class SleepThread implements Runnable {
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "starting sleep now.");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "finished sleeping.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new SleepThread());
        Thread thread2 = new Thread(new SleepThread());

        thread1.start();
        thread2.start();
    }
}
