package homework9;

public class MainCacheService {
    public static void main(String[] args) throws Throwable {
        Service service = CachedInvocationHandler.cache(new ServiceImpl());
        //run(service);
        run1(service);
        CachedInvocationHandler.concatenateMainAndTempFiles();
    }

    public static void run(Service service){
        System.out.println("Result is: "+service.doHardWork("A", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("B", 23) + "\n");
        System.out.println("Result is: "+service.doHardWork("A", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("C", 23) + "\n");
        System.out.println("Result is: "+service.doHardWork("A", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("A", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("C", 23) + "\n");
        System.out.println("Result is: "+service.doHardWork("D", 70) + "\n");
    }

    public static void run1(Service service){
        // Cached data
        run(service);

        // New data
        System.out.println("Result is: "+service.doHardWork("E", 222) + "\n");
        System.out.println("Result is: "+service.doHardWork("R", 333) + "\n");
        System.out.println("Result is: "+service.doHardWork("T", 444) + "\n");
        System.out.println("Result is: "+service.doHardWork("E", 222) + "\n");
        System.out.println("Result is: "+service.doHardWork("R", 333) + "\n");
        System.out.println("Result is: "+service.doHardWork("T", 444) + "\n");
    }
}
