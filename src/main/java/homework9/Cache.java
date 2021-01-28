package homework9;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    String cacheType();//default "IN_MEMORY";
    Class[] identityBy() default {String.class, Integer.class};
    long countList() default 0;
}
