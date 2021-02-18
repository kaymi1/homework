package SpringFunc.ClassesForProxy;

import javax.annotation.PostConstruct;

@Profiling
public class QuoterImpl implements Quoter{
    private String message;
    private int repeat;

    @PostConstruct
    private void init(){
        repeat = 10;
    }

    @Override
    public void saySmth() {
        for (int i = 0; i < getRepeat(); i++) {
            System.out.println(message);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}
