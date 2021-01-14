package homework5;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class TerminalImplTest {
    private TerminalImpl terminal;
    private TerminalServer terminalServer;
    private PinValidator pinValidator;
    private ShowText showText;

    @BeforeClass
    public void setUp(){
        HashSet<Account> listAccount = new HashSet<>();
        listAccount.add(new Account("alex", "1234", 12000));
        listAccount.add(new Account("harry", "4321", 12200));
        listAccount.add(new Account("red", "1111", 32500));
        terminalServer = new TerminalServer(listAccount);
        pinValidator = new PinValidator();
        showText = new ShowText();
    }

    @Test
    public void test(){
        terminal.init();
    }
}