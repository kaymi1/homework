package SerializationFunc;

import java.io.Serializable;

public class Person implements Serializable {
    private Object age;
    private Object name;
    private Object profession;
    private static final long serialVersionUID = 1L;

    public Person(){}
    public Person(Object age, Object name, Object profession){
        this.setAge(age);
        this.setName(name);
        this.setProfession(profession);
    }

    public Object getAge() {
        return age;
    }

    public void setAge(Object age) {
        this.age = age;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getProfession() {
        return profession;
    }

    public void setProfession(Object profession) {
        this.profession = profession;
    }
}
