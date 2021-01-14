package homework3test;

import homework3.IntegerShmoption;
import homework3.NumberShmoption;
import homework3.Shmoption;

public class TestGenericFunc {


    public static void main(String[] args) {
        Shmoption<String> string = new Shmoption<>("yes");
        Shmoption<Integer> integer = new Shmoption<>(21);
        System.out.println(string.isPresent());
        System.out.println(string.get());
        System.out.println(integer.isPresent());
        System.out.println(integer.get());

        Shmoption<?> present = new Shmoption<>("yes");
        Object value = present.get();
        System.out.println(value);

        Shmoption<? extends Number> number = new Shmoption<>(123);
        Number n = number.get();


        NumberShmoption<?> num = new NumberShmoption<>(123);
        Number m = num.get();

        double d = getDoubleValue(new IntegerShmoption(123));

        NumberShmoption<Number> numm = new NumberShmoption<>(123);
        setInteger(numm);
    }

    // producer
    static double getDoubleValue(Shmoption<? extends  Number> shmopt){
        return shmopt.get().doubleValue();
    }

    // consumer
    static void setInteger(Shmoption<? super Integer> shmopt){
        shmopt.set(222);
    }
}
