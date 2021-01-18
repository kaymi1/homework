package homework9;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {
    private final Map<Object, Object> memoryCache = new HashMap<>();
    private final Object delegate;
    private final String filename = "cache.file";
    private FileOutputStream fos;
    private ObjectOutput outstr;
    private Object resultFromChache;
    private FileInputStream fis;
    private ObjectInput in;

    public CachedInvocationHandler(Object delegate) {
        this.delegate = delegate;
        try {
//            fos = new FileOutputStream(filename);
//            outstr = new ObjectOutputStream(fos);
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate));
    }

    public void close() {
        try {
            System.out.println("Rewrite the file");
//            Method method = outstr.getClass().getDeclaredMethod("drain");
//            method.setAccessible(true);
//            method.invoke(outstr);
            outstr.flush();
            outstr.close();
        } catch (IOException e) {
            System.out.println("IOException when it is closed");
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) return invoke(method, args);
        Cache cache = method.getAnnotation(Cache.class);
        Object result = null;
        if (cache.cacheType().equals("IN_MEMORY")) {
            if (!memoryCache.containsKey(key(method, args))) {
                System.out.println("Delegation to " + method.getName());
                result = invoke(method, args);
                memoryCache.put(key(method, args), result);
            }
            return memoryCache.get(key(method, args));
        } else if (cache.cacheType().equals("FILE")) {
            if (!isContainsCacheInFile(method, args)) {
                if (args[0].toString().equals("close")) {
                    close();
                    return 1.0;
                }
                System.out.println("Delegation to " + method.getName());
                result = invoke(method, args);
                CacheList cacheList = new CacheList(args, result);
                outstr.writeObject(cacheList);
                return result;
            } else {
                System.out.format("Result from the cache file for [%s] with param [%s] [%s]\n",
                        method.getName(), args[0], args[1]);
                return resultFromChache;
            }
        }
        return 0;
    }


    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

    private boolean isArgsTheSame(Object[] src, Object[] dst) {
        for (int i = 0; i < src.length; i++) {
            if (!src[i].toString().equals(dst[i].toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean isContainsCacheInFile(Method method, Object[] args) throws Throwable {
        try {
            for (; ; ) {
                CacheList cacheList = (CacheList) in.readObject();
                if (isArgsTheSame(args, cacheList.getArgs())) {
                    resultFromChache = cacheList.getResult();
                    return true;
                }
            }
        } catch (EOFException e) {
        }
        return false;
    }
}
