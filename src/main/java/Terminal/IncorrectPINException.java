package Terminal;

public class IncorrectPINException extends Exception{
    @Override
    public String getMessage(){
        return "Incorrect PIN";
    }
}
