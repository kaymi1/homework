package homework8;

import java.io.*;

public class Serialize {
    public static void main(String[] args) throws Throwable{
        String fileName = "person.ser";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            Person person = new Person(23, "Alex", "programmer");
            Person person1 = new Person(25, "Adren", "non-programmer");
            out.writeObject(person);
            out.writeObject(person1);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

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
