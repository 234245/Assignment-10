class BasicThread implements Runnable {
    public void run() {
        System.out.println("This is a basic thread running.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new BasicThread());
        thread.start();
    }
}
