package homework8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Desirialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "person.ser";

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fis);
        Person person = (Person) in.readObject();
        Person person1 = (Person) in.readObject();
        System.out.format("Person's name is %s, he is %s old and his profession is a %s\n",
                person.getName(), person.getAge(), person.getProfession());
        System.out.format("Person's name is %s, he is %s old and his profession is a %s",
                person1.getName(), person1.getAge(), person1.getProfession());
    }
}
