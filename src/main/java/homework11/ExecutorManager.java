package homework11;

import java.util.List;

public interface ExecutorManager {
    Context execute(Runnable callback, List<Runnable> tasks);
}
