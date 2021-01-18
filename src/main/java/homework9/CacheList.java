package homework9;

import java.io.Serializable;
import java.lang.reflect.Method;

public class CacheList implements Serializable {
    private final Object[] args;
    private final Object result;
    private static final long serialVersionUID = 1L;


    public CacheList(Object[] args, Object result) {
        this.args = args;

        this.result = result;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getResult() {
        return result;
    }
}
