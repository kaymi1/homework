package homework11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Task<T>{
    Callable<? extends T> task;
    ConcurrentMap<Callable<? extends T>, T> cache;
    ReentrantLock locker = new ReentrantLock();

    public Task(Callable<? extends T> task){
        this.task = task;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            Thread.sleep(5000);
            return Thread.currentThread().getName();
        };
        //Task<String> taskFuture = new Task<>(task);
        FutureTask<?> future = new FutureTask<>(task);
        List<Thread> listThread = new ArrayList<>();
        new Thread(future, "Thread").start();
        System.out.println(future.get());
    }

    public T get() throws RuntimeException{
        locker.lock();
        T resultCall;
        try {
            resultCall = task.call();
            return resultCall;
        } catch (Exception e) {
            throw new RuntimeException("Runtime exception", e);
        } finally {
            locker.unlock();
        }
    }
}
