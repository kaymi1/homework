package homework2test;

import homework2.CounterWordsInText;
import homework2.CreateText;

import java.util.*;


public class TestCounterWords {


    public static void main(String[] args) {

        CreateText createText = new CreateText();
        //CounterWordsInText counter = new CounterWordsInText(createText.createText());
        //counter.returnWordsInFile();
        //System.out.println(counter.counterOriginWords());
        //counter.printWordsInFile();
        //counter.printCounterWords();
        //counter.sortedWordsLength();
        //counter.printSortedWords();

        CounterWordsInText counter = new CounterWordsInText(createText.createText());
        //counter.reverseTextRow();

        //Iterator it = counter.iterator();
        //while (it.hasNext()){
            //System.out.println(it.next());
        //}

        counter.setUserListValueForOutput(new ArrayList<>(
                Arrays.asList(2, 5)
        ));

        counter.showRowValue();
        counter.printOutputRows();
    }
}
