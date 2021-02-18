package MultitheradingFuture;

import CustomFixedAndScalarThreadPool.FixedTreadPool;

import java.util.List;

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
