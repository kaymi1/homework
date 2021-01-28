package homework10;

public class ThreadExample implements Runnable{
    Thread t;

    public ThreadExample(){
        t = new Thread(this, "Thread2");
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Thread " + t.getName() + " is starting");
        for (int i = 0; i < 5; i++) {
            System.out.printf("Do work %d with thread: %s%n", i, t.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Thread %s is finished\n", t.getName());
    }

    public static void main(String[] args) {
        new ThreadExample();

        System.out.println("Thread " + Thread.currentThread().getName() + " is starting");
        for (int i = 0; i < 5; i++) {
            System.out.printf("Do work %d with thread: %s%n", i, Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Thread %s is finished\n", Thread.currentThread().getName());
    }
}
