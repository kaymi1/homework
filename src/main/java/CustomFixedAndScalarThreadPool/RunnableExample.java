package CustomFixedAndScalarThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%d - thread %s is running%n", i, Thread.currentThread().getName());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Runnable> runnables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            runnables.add(new RunnableExample());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (Runnable runnable : runnables) {
            executorService.execute(runnable);
        }
    }
}
