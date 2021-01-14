package homework7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;


public class ClassLoaderExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> b = ClassLoaderExample.class.getClassLoader().loadClass("homework7.B");
        Object obj = b.newInstance();
        Method method = obj.getClass().getMethod("doSmthElse");
        method.invoke(obj);
    }
}
