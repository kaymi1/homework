package CacheProxy;

import java.util.List;

public interface Service {
    @Cache(cacheType = "FILE", identityBy = {String.class, Integer.class})
    double doHardWork(String name, Integer num);

    @Cache(cacheType = "FILE", identityBy = {String.class}, countList = 100_000)
    List<String> doHardWorkWithList(String item);
}
