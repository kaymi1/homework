package SpringFunc.Launchers;

import JDBCFucn.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class LauncherXmlHW {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Calculator calculator = context.getBean(Calculator.class);
        List<Integer> result1 = calculator.fibonachi(9);
        System.out.println(result1.toString());
        List<Integer> result2 = calculator.fibonachi(5);
        System.out.println(result2.toString());
        List<Integer> result3 = calculator.fibonachi(3);
        System.out.println(result3.toString());
    }
}
