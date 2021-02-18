package MultitheradingFuture;

import java.util.concurrent.Callable;

public class CallableImpl<T> implements Callable<T> {
    @Override
    public T call() throws Exception {
        Thread.sleep(1000);
        return (T) Thread.currentThread().getName();
    }
}
