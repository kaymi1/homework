package homework11;

import homework10.FixedTreadPool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorManagerImpl implements ExecutorManager{
    private final FixedTreadPool executorService =
            new FixedTreadPool(5);

    @Override
    public Context execute(Runnable callback, List<Runnable> tasks) {
        for (Runnable task : tasks) {
            executorService.execute(task);
        }
        executorService.execute(callback);
        executorService.start();
        return new ContextImpl(executorService, callback, tasks);
    }
}
