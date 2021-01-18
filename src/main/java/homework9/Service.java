package homework9;

public interface Service {
    @Cache(cacheType = "FILE")
    double doHardWork(String name, Integer num);
}
