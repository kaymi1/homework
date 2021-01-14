package homework5;

public class AccountIsLockedException extends Exception{

    @Override
    public String getMessage(){
        return "Your account is blocked! It continue about ";
    }
}
