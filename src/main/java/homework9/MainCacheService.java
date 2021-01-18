package homework9;

public class MainCacheService {
    public static void main(String[] args) {
        Service service = CachedInvocationHandler.cache(new ServiceImpl());
        run(service);

    }

    public static void run(Service service){
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work2", 23) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work43", 70) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        /*System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work43", 70) + "\n");
        System.out.println("Result is: "+service.doHardWork("work2", 23) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work2222", 666) + "\n");
        System.out.println("Result is: "+service.doHardWork("work", 21) + "\n");
        System.out.println("Result is: "+service.doHardWork("work2222", 666) + "\n");*/
        System.out.println("Result is: "+service.doHardWork("close", 666) + "\n");
    }
}
