package homework10;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
}
