package homework11;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureExample {
    public static void main(String[] args) throws Exception {
        Callable<String> task = () -> {
            System.out.println("Thread before sleep");
            Thread.sleep(3000);
            return "This is a future example";
        };
        FutureTask<String> future = new FutureTask<>(task);
        new Thread(future).start();
        //System.out.println(future.get());
    }
}
