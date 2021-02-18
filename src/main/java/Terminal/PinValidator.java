package Terminal;

import java.util.Scanner;

public class PinValidator {
    private String PIN;
    private final Scanner in = new Scanner(System.in);

    public boolean validatePIN(String pin){
        char[] characters = pin.toCharArray();
        for (char character: characters) {
            if(!(character >= '1' && character <= '9')){
                System.out.println("PIN is not valid");
                return false;
            }
        }
        setPIN(pin);
        return true;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
