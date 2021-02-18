package SerializationFunc;

import java.io.*;

public class Serialize {
    public static void main(String[] args) throws Throwable {
        String fileName = "person.ser";
        Person personS0 = new Person(23, "Alex", "programmer");
        Person personS1 = new Person(25, "Adren", "non-programmer");

//        FileOutputStream fos = new FileOutputStream(fileName);
//        ObjectOutputStream out = new ObjectOutputStream(fos);
//        out.writeObject(personS0);


        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fis);
        Person person = (Person) in.readObject();

        //out.writeObject(personS1);
        FileInputStream fis1 = new FileInputStream(fileName);
        ObjectInputStream in1 = new ObjectInputStream(fis1);
        Person person1 = (Person) in1.readObject();

        System.out.format("Person's name is %s, he is %s old and his profession is a %s\n",
                person.getName(), person.getAge(), person.getProfession());
        System.out.format("Person's name is %s, he is %s old and his profession is a %s",
                person1.getName(), person1.getAge(), person1.getProfession());
    }
}
