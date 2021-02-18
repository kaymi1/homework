package CacheProxy;

import java.io.Serializable;

public class CacheList implements Serializable {
    private Object[] args;
    private Object result;
    //private static final long serialVersionUID = 10L;


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
