package CounterWordsInTextAndCustomIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomIterator<T> implements Iterator<T> {

    private CounterWordsInText counter;
    private ArrayList<T> list;
    private int current;

    public CustomIterator(CounterWordsInText counter, ArrayList<T> list){
        this.counter = counter;
        this.list = list;
        this.current = list.size() - 1;
    }

    public CustomIterator(ArrayList<T> list){
        this.list = list;
        this.current = list.size() - 1;
    }


    @Override
    public boolean hasNext() {
        return current != -1;
    }

    public boolean hasPrevious() {
        return current != -1;
    }

    public T previous(){
        return list.get(current--);
    }

    @Override
    public T next() {
        return list.get(current--);
    }
}
