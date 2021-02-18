package CustomListIterator;

import java.util.LinkedList;
import java.util.ListIterator;

public class MyList<T> {
    private final MyIterator<T> iterator;
    private LinkedList<T> list;


    public MyList(LinkedList<T> list){
        this.setList(list);
        this.iterator = new MyIterator<>(list);
    }

    public ListIterator<T> iterator(){
        return iterator;
    }

    public LinkedList<T> getList() {
        return list;
    }

    public void setList(LinkedList<T> list) {
        this.list = list;
    }
}
