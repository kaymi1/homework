package homework9;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> doHardWorkWithList(String item) {
        System.out.format("Do hard work with param [%s]\n", item);
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            resultList.add(item+ ": " + i);
        }
        return resultList;
    }
}
