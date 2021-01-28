package homework10;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScalableThreadPool implements ThreadPool{
    private java.util.Queue<Runnable> queueTasks;
    private boolean executeSet = false;
    private int minThreads;
    private int maxThreads;
    private int amountTasks = 0;
    private Queue<Thread> listTreads;

    public ScalableThreadPool(int minThreads, int maxThreads){
        this.maxThreads = maxThreads;
        this.minThreads = minThreads;
        queueTasks = new ConcurrentLinkedQueue<>();
        listTreads = new ConcurrentLinkedQueue<>();
    }

    public static void main(String[] args) {
        ThreadPool executorService = new ScalableThreadPool(3, 5);
        for (int i = 0; i < 13; i++) {
            int finalI = i;
            executorService.execute(()->{
                try {
                    System.out.printf("%s is working\n", Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s have done hard work with %d task\n", Thread.currentThread().getName(),finalI);
            });
        }
        executorService.start();
    }

    @Override
    synchronized public void start() {
        while (!queueTasks.isEmpty()){
            int i = 0;
            int necessaryAmountThreads = queueTasks.size() >= maxThreads ? maxThreads : minThreads;
            while(!queueTasks.isEmpty() && necessaryAmountThreads != i){
                Runnable task = queueTasks.poll();
                Thread t = new Thread(task, "Thread" + i++);
                t.start();
                listTreads.add(t);
            }
            System.out.println("---------------------------------------");
            while (!listTreads.isEmpty()){
                Thread t = listTreads.poll();
                try {
                    // i can't say that 3 threads are running in the same time ->
                    // -> period cycle to start 3 threads one more time is equal the working time of the slowest task
                    // task1 = 10 sec, task2 = 30 sec, task3 = 3 sec --> period cycle = 30 sec (task2)
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("---------------------------------------");
        }
        System.out.println("All the tasks were executed\n");
    }

    @Override
    synchronized public void execute(Runnable runnable) {
        queueTasks.add(runnable);
        System.out.printf("Task %d was added to pool tasks\n", amountTasks++);
    }
}
