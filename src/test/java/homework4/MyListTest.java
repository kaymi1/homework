package homework4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MyListTest {

    private MyList<Integer> myList;

    @Before
    public void setUp(){
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(22, 23, 24, 25, 26));
        myList = new MyList<>(list);
    }


    @Test
    public void testIteratorNext(){
        LinkedList<Object> listCheck = new LinkedList<>();
        ListIterator<?> it = myList.iterator();
        while(it.hasNext()){
            listCheck.add(it.next());
        }

        Assert.assertArrayEquals(myList.getList().toArray(), listCheck.toArray());
    }


    @Test
    public void testIteratorPrevious(){
        LinkedList<Object> listCheck = new LinkedList<>();
        ListIterator<?> it = myList.iterator();
        while(it.hasPrevious()){
            listCheck.add(it.previous());
        }

        Collections.reverse(myList.getList());
        assertArrayEquals(myList.getList().toArray(), listCheck.toArray());
    }
}