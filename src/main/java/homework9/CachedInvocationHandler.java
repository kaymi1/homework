package homework9;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {
    private final Map<Object, Object> memoryCache = new HashMap<>();
    private final Object delegate;
    private final static String filename = "cache.file";
    private final static String filenameTemp = "cacheTemp.file";
    private final Boolean isMainFileExist;
    private Class[] identityBy;
    private FileOutputStream fos;
    private ObjectOutput outstr;
    private Object resultFromCache;
    private FileInputStream fis0;
    private ObjectInput in0;

    public CachedInvocationHandler(Object delegate) {
        this.delegate = delegate;
        isMainFileExist = new File(filename).exists();
        if (isMainFileExist) {
            try {
                fos = new FileOutputStream(filenameTemp);
                outstr = new ObjectOutputStream(fos);
            } catch (IOException e) {
                System.out.println("IOException");
            }
        } else {
            try {
                fos = new FileOutputStream(filename);
                outstr = new ObjectOutputStream(fos);
            } catch (IOException e) {
                System.out.println("IOException");
            }
        }
    }

    public static <T> T cache(Object delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate));
    }

    public static void concatenateMainAndTempFiles() throws Throwable{
        Boolean isMainFileExist = new File(filename).exists();
        Boolean isTempFileExist = new File(filenameTemp).exists();
        List<CacheList> cacheLists = new ArrayList<>();
        if (isMainFileExist && isTempFileExist) {
            FileInputStream fis0 = new FileInputStream(filename);
            ObjectInputStream in0 = new ObjectInputStream(fis0);
            try {
                for (; ; ) {
                    cacheLists.add((CacheList) in0.readObject());
                }
            } catch (EOFException e) {}
            FileInputStream fis1 = new FileInputStream(filenameTemp);
            ObjectInputStream in1 = new ObjectInputStream(fis1);
            try {
                for (; ; ) {
                    cacheLists.add((CacheList) in1.readObject());
                }
            } catch (EOFException e) {}
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream outstr = new ObjectOutputStream(fos);
            for (CacheList cacheObject : cacheLists) {
                outstr.writeObject(cacheObject);
            }
        }
    }

    public Object delegationToMethod(Method method, Object[] args) throws Throwable{
        System.out.println("Delegation to " + method.getName());
        return invoke(method, args);
    }

    public void getFromCache(Method method, Object[] args){
        System.out.format("Result from the cache file for [%s] with param [%s] [%s]\n",
                method.getName(), args[0], args[1]);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) return delegationToMethod(method, args);
        Cache cache = method.getAnnotation(Cache.class);
        identityBy = cache.identityBy();
        Object result = null;
        if (cache.cacheType().equals("IN_MEMORY")) {
            if (!memoryCache.containsKey(key(method, args))) {
                result = delegationToMethod(method, args);
                /* TODO: use reflect for countList member
                Class<?> obj = result.getClass();
                if(obj == List.class){
                    List<String> resTemp = new ArrayList<String>(result);
                    List<String> list = new ArrayList<>();
                    for (String s : result) {

                    }
                }*/
                memoryCache.put(key(method, args), result);
            }
            return memoryCache.get(key(method, args));
        } else if (cache.cacheType().equals("FILE")) {
            if (isContainsCacheInFile(method, args)) {
                getFromCache(method, args);
                return resultFromCache;
            } else {
                result = delegationToMethod(method, args);
                CacheList cacheList = new CacheList(args, result);

                // if the main file exists write the object to the filenameTemp file
                // if it doesn't write the object to the filename file
                outstr.writeObject(cacheList);
                return result;
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
        if(identityBy.length == 2){
            for (int i = 0; i < src.length; i++) {
                if (!src[i].toString().equals(dst[i].toString())) {
                    return false;
                }
            }
        }
        else if(identityBy[0] == String.class){
            if (!src[0].toString().equals(dst[0].toString())) {
                return false;
            }
        }
        else if(identityBy[0] == Integer.class){
            if (!src[1].toString().equals(dst[1].toString())) {
                return false;
            }
        }
        return true;
    }
    private boolean checkCacheInFile(Object[] args, String filename) throws Throwable{
        FileInputStream fis0 = new FileInputStream(filename);
        ObjectInputStream in0 = new ObjectInputStream(fis0);
        try {
            for (; ; ) {
                CacheList cacheList = (CacheList) in0.readObject();
                if (isArgsTheSame(args, cacheList.getArgs())) {
                    resultFromCache = cacheList.getResult();
                    return true;
                }
            }
        } catch (EOFException e) {}
        return false;
    }

    private boolean isContainsCacheInFile(Method method, Object[] args) throws Throwable {
        boolean isContainsInMainFile = checkCacheInFile(args, filename);
        if (isMainFileExist) {
            return isContainsInMainFile || checkCacheInFile(args, filenameTemp);
        }
        return isContainsInMainFile;
    }
}
