package CustomFixedAndScalarThreadPool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedTreadPool implements ThreadPool{
    private Queue<Runnable> queueTasks;
    private boolean executeSet = false;
    private int amountThreads;
    private int amountTasks;
    private Queue<Thread> listTreads;

    public FixedTreadPool(int amountThreads){
        this.amountThreads = amountThreads;
        setQueueTasks(new ConcurrentLinkedQueue<>());
        setListTreads(new ConcurrentLinkedQueue<>());
    }

    public static void main(String[] args) {
        ThreadPool executorService = new FixedTreadPool(3);
        for (int i = 0; i < 10; i++) {
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
    synchronized public void start() throws RuntimeException{
        while (!getQueueTasks().isEmpty()){
            int i = 0;
            while(!getQueueTasks().isEmpty() && amountThreads != i){
                Runnable task = getQueueTasks().poll();
                Thread t = new Thread(task, "Thread" + i++);
                t.start();
                getListTreads().add(t);
            }
            System.out.println("---------------------------------------");
            while (!getListTreads().isEmpty()){
                Thread t = getListTreads().poll();
                try {
                    // i can't say that 3 threads are running in the same time ->
                    // -> period cycle to start 3 threads one more time is equal the working time of the slowest task
                    // task1 = 10 sec, task2 = 30 sec, task3 = 3 sec --> period cycle = 30 sec (task2)
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException("The task was interrupted");
                }
            }
            System.out.println("---------------------------------------");
        }
        System.out.println("All the tasks were executed\n");
    }

    @Override
    synchronized public void execute(Runnable runnable) {
        getQueueTasks().add(runnable);
        System.out.printf("Task %d was added to pool tasks\n", amountTasks++);
    }

    public Queue<Runnable> getQueueTasks() {
        return queueTasks;
    }

    public void setQueueTasks(Queue<Runnable> queueTasks) {
        this.queueTasks = queueTasks;
    }

    public Queue<Thread> getListTreads() {
        return listTreads;
    }

    public void setListTreads(Queue<Thread> listTreads) {
        this.listTreads = listTreads;
    }

    public int getAmountTasks() {
        return amountTasks;
    }

    public void setAmountTasks(int amountTasks) {
        this.amountTasks = amountTasks;
    }
}
