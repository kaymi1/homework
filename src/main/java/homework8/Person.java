package homework8;

import java.io.Serializable;

public class Person implements Serializable {
    private int age;
    private String name;
    private String profession;
    private static final long serialVersionUID = 1L;

    public Person(){}
    public Person(int age, String name, String profession){
        this.setAge(age);
        this.setName(name);
        this.setProfession(profession);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
