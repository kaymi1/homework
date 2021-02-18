package MultitheradingFuture;

import java.util.concurrent.*;

public class CustomTask<T> implements Future<T> {

    private Callable<? extends T> callable;

    public CustomTask(Callable<? extends T> callable){
        this.callable = callable;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
          Thread.sleep(2000);
          return Thread.currentThread().getName();
        };
        CustomTask<String> futureTask = new CustomTask<>(task);
        //new Thread(futureTask);
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        try {
            return this.callable.call();
        } catch (Exception e) {
            throw new RuntimeException("Runtime exception", e);
        }
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
