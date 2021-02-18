package SpringFunc.ClassesForProxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class<?> beanClass = o.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)){
            map.put(s, beanClass);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        Class beanClass = map.get(s);
        if(beanClass != null){
            return Proxy.newProxyInstance(
                    o.getClass().getClassLoader(),
                    o.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("Start profiling");
                            Object retVal = method.invoke(o, args);
                            System.out.println("End profiling");
                            return retVal;
                        }
                    }
            );
        }
        return o;
    }
}
