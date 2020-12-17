package homework2;

import java.util.ArrayList;

public class CreateText {
    public ArrayList<String> createArrayText(){
        String text = "This section has been specifically designed to provide detailed insights into which elements " +
                "of the CODA Audio range will best suit any given scenario.";
        String text1 = "Another english text CODA CODA CODA as provide.";
        String text2 = "I think it is also english text.";
        String text3 = "The spatial resolution of first-order Ambisonics as described above is quite low.";
        String text4 = "This is the text.";
        ArrayList<String> arrayText = new ArrayList<String>();
        arrayText.add(text);
        arrayText.add(text1);
        arrayText.add(text2);
        arrayText.add(text3);
        arrayText.add(text4);


        return arrayText;
    }

    public String createText(){
        return "Java is a class-based, object-oriented programming \n" +
                        "language that is designed to have as few implementation \n" +
                        "dependencies as possible. It is a general-purpose programming \n" +
                        "language intended to let application developers write once, \n" +
                        "run anywhere, meaning that compiled Java code can run on all \n" +
                        "platforms that support Java without the need for recompilation.";

    }
}
