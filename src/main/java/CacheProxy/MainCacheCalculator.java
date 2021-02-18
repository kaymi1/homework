package CacheProxy;

public class MainCacheCalculator {
    public static void main(String[] args) {
        Calculator calculator = CachedInvocationHandler.cache(new CalculatorImpl());
        run(calculator);
    }
    private static void run(Calculator calculator){
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(6));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
    }
}
