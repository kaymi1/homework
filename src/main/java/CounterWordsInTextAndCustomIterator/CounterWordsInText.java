package CounterWordsInTextAndCustomIterator;

import java.util.*;

public class CounterWordsInText{

    public CounterWordsInText(Set<String> arrayWords, ArrayList<String> arrayText){
        this.setArrayWords(arrayWords);
        this.setArrayText(arrayText);
        this.counterWords = new HashMap<>();
    }

    public CounterWordsInText(ArrayList<String> arrayText){
        this.setArrayText(arrayText);
        this.setArrayWords(new HashSet<>());
        this.counterWords = new HashMap<>();
    }

    public CounterWordsInText(String text){
        this.setText(text);
        this.setArrayWords(new HashSet<>());
        this.counterWords = new HashMap<>();
    }

    public CounterWordsInText(){
        this.setArrayWords(new HashSet<>());
        this.counterWords = new HashMap<>();
    }


    private Set<String> arrayWords;
    private Set<String> sortedWordsLength;
    private Map<String, Integer> counterWords;
    private Map<Integer,String> outputRows;
    private ArrayList<String> arrayText;
    private String text;
    private Iterator<Integer> myIterator;
    private ArrayList<Integer> listForIterator;
    private ArrayList<Integer> userListValueForOutput;

    public int counterOriginWords(){
        return getArrayWords().size();
    }

    public Set<String> setWordsFromFile(){
        StringBuilder word;
        Integer j;

        for (String text: getArrayText()) {
            word = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                if((text.charAt(i) >= 'a' && text.charAt(i) <= 'z') ||
                        (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')){
                    word.append(text.charAt(i));
                    continue;
                }

                if( (text.charAt(i) != ' ') && ((text.charAt(i) <= '@') || (text.charAt(i) >= ']' && text.charAt(i) <= '`') ||
                        (text.charAt(i) >= '{'))){
                    continue;
                }

                if(getArrayWords().contains(word.toString())){
                    j = getCounterWords().get(word.toString());
                    getCounterWords().put(word.toString(),  j.intValue() + 1);
                    word = new StringBuilder();
                    continue;
                }

                getArrayWords().add(word.toString());
                getCounterWords().put(word.toString(), new Integer(1));
                word = new StringBuilder();
            }
        }
        return arrayWords;
    }

    public void sortedWordsLength(){
        setSortedWordsLength(new TreeSet<String>((o1, o2) -> {
            if(o1.length() < o2.length()){
                return -2;
            }
            if(o1.length() == o2.length()){
                return -1;
            }
            return 1;
        }));

        getSortedWordsLength().addAll(getArrayWords());
    }

    public void reverseTextRow(){
        LinkedList<String> st = returnRowsFromFile();
        ListIterator li = st.listIterator(st.size());
        while (li.hasPrevious()){
            System.out.println(li.previous());
        }
    }

    public LinkedList<String> returnRowsFromFile(){
        LinkedList<String> st = new LinkedList<>();
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < getText().length(); i++) {
            if(getText().charAt(i) == '\n'){
                st.add(buff.toString());
                buff = new StringBuffer();
                continue;
            }
            buff.append(getText().charAt(i));
        }
        st.add(buff.toString());
        return st;
    }

    public void relationRowValue(){
        LinkedList<String> st = returnRowsFromFile();
        outputRows = new HashMap<>();
        for (int i = 0; i < st.size(); i++) {
            outputRows.put(i,st.get(i));
        }
    }

    public void showUserRowValue(){
        relationRowValue();
        for (Integer i: getUserListValueForOutput()) {
            System.out.println( "("  + i + ") " + outputRows.get(i));
        }
    }

    public Iterator<Integer> iterator(ArrayList<Integer> list){
        setListForIterator(list);
        myIterator =  new CustomIterator<Integer>(this, getListForIterator());
        return myIterator;
    }

    public void printCounterWords(){
        Iterator it = getCounterWords().entrySet().iterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

    public void printOutputRows(){
        Iterator it = outputRows.entrySet().iterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

    public void printWordsFromFile(){
        Iterator it = getArrayWords().iterator();
        it.forEachRemaining(System.out::println);
    }

    public void printSortedWords(){
        Iterator it = getSortedWordsLength().iterator();
        it.forEachRemaining(System.out::println);
    }

    public Map<String, Integer> getCounterWords() {
        return counterWords;
    }

    public void setCounterWords(Map<String, Integer> counterWords) {
        this.counterWords = counterWords;
    }

    public Set<String> getArrayWords() {
        return arrayWords;
    }

    public void setArrayWords(Set<String> arrayWords) {
        this.arrayWords = arrayWords;
    }

    public ArrayList<String> getArrayText() {
        return arrayText;
    }

    public void setArrayText(ArrayList<String> arrayText) {
        this.arrayText = arrayText;
    }

    public Set<String> getSortedWordsLength() {
        return sortedWordsLength;
    }

    public void setSortedWordsLength(Set<String> sortedWordsLength) {
        this.sortedWordsLength = sortedWordsLength;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Integer> getListForIterator() {
        return listForIterator;
    }

    public void setListForIterator(ArrayList<Integer> listForIterator) {
        this.listForIterator = listForIterator;
    }

    public ArrayList<Integer> getUserListValueForOutput() {
        return userListValueForOutput;
    }

    public void setUserListValueForOutput(ArrayList<Integer> userListValueForOutput) {
        this.userListValueForOutput = userListValueForOutput;
    }
}
