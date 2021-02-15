package homework11;

import homework10.FixedTreadPool;

import java.util.List;

public class ContextImpl implements Context{
    private FixedTreadPool executor;
    private Runnable callback;
    private List<Runnable> tasks;

    public ContextImpl(FixedTreadPool executor, Runnable callback, List<Runnable> tasks){
        this.executor = executor;
        this.callback = callback;
        this.tasks = tasks;
    }

    @Override
    public int getCompletedTaskCount() {
        return executor.getAmountTasks() - executor.getQueueTasks().size();
    }

    @Override
    public int getFailedTaskCount() {
        return 0;
    }

    @Override
    public int getInterruptedTaskCount() {
        return executor.getListTreads().size();
    }

    @Override
    public void interrupt() {
        for (Thread tread : executor.getListTreads()) {
            tread.interrupt();
        }
    }

    @Override
    public boolean isFinished() {
        return executor.getQueueTasks().isEmpty();
    }
}
