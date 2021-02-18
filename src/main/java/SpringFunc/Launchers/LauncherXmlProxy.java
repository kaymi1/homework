package SpringFunc.Launchers;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import SpringFunc.ClassesForProxy.Quoter;

public class LauncherXmlProxy {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context-proxy");
        context.getBean(Quoter.class).saySmth();
    }
}
