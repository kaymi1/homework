package homework2test;

import homework2.CounterWordsInText;
import homework2.CreateText;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class CounterWordsTest {

    private CounterWordsInText counterText;
    private CounterWordsInText counterArrayText;
    private String exampleText1;
    private String exampleText2;
    private ArrayList<String> arrayText;

    @Before
    public void setUp(){
        exampleText1 = "This is the text";
        exampleText2 = "This is another text";
        arrayText = new ArrayList<>();
        arrayText.add(exampleText1);
        arrayText.add(exampleText2);

        counterArrayText = new CounterWordsInText(arrayText);

        counterText = new CounterWordsInText(exampleText1);
    }

    @Test
    public void testReturnWordsFromFile(){
        Set<?> arrayWords = counterArrayText.setWordsFromFile();

        Assert.assertArrayEquals(arrayWords.toArray(), counterArrayText.getArrayWords().toArray());
    }

    public static void main(String[] args) {

        CreateText createText = new CreateText();
        CounterWordsInText counter = new CounterWordsInText(createText.createArrayText());
        //counter.returnWordsInFile();
        //System.out.println(counter.counterOriginWords());
        //counter.printWordsInFile();
        //counter.printCounterWords();
        //counter.sortedWordsLength();
        //counter.printSortedWords();


        //CounterWordsInText counter = new CounterWordsInText(createText.createText());
        //counter.reverseTextRow();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(18, 19, 20, 21, 22, 23));
        Iterator it = counter.iterator(list);
        while (it.hasNext()){
            System.out.println(it.next());
        }

        //counter.setUserListValueForOutput(new ArrayList<>(
       //         Arrays.asList(2, 5)
        //));

       // counter.showUserRowValue();
       // counter.printOutputRows();
    }
}
