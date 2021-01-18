package homework9;

public interface Calculator {
    @Cache(cacheType = "IN_MEMORY")
    int calc(int arg);
}
