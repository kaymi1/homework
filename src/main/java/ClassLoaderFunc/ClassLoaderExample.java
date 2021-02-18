package ClassLoaderFunc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ClassLoaderExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> b = ClassLoaderExample.class.getClassLoader().loadClass("ClassLoaderFunc.B");
        Object obj = b.newInstance();
        Method method = obj.getClass().getMethod("doSmthElse");
        method.invoke(obj);
    }
}
