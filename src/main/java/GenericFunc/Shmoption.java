package GenericFunc;

import java.util.NoSuchElementException;

public class Shmoption<T> {
    T value;

    public Shmoption(T value) {this.value = value;}

    public T get(){
        if(value == null){
            throw new NoSuchElementException();
        }
        return value;
    }

    public void set(T newValue) {value = newValue;}

    public T orElse(T other) {return value == null ? other:value;}

    public boolean isPresent() {return value != null;}
}
