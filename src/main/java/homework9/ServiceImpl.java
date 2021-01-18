package homework9;

public class ServiceImpl implements Service{
    @Override
    public double doHardWork(String name, Integer num) {
        System.out.format("Do hard work with param [%s] [%d]\n", name, num);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (name.length() + num)/Math.exp(1);
    }
}
