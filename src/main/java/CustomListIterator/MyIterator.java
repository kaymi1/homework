package CustomListIterator;

import java.util.LinkedList;
import java.util.ListIterator;

public class MyIterator<T> implements ListIterator<T> {
    private final LinkedList<T> list;
    private int currentEnd;
    private int currentStart;


    public MyIterator(LinkedList<T> list){
        this.list = list;
        this.currentEnd = list.size() - 1;
        this.currentStart = 0;
    }

    @Override
    public boolean hasNext() {
        return currentStart < list.size();
    }

    @Override
    public T next() {
        return list.get(currentStart++);
    }

    public boolean hasPrevious() {
        return currentEnd > -1;
    }

    public T previous(){
        return list.get(currentEnd--);
    }

    @Override
    public int nextIndex() {
        return currentStart;
    }

    @Override
    public int previousIndex() {
        return currentEnd;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(T t) {

    }

    @Override
    public void add(T t) {

    }
}

