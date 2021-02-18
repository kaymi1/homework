package Terminal;

import java.util.*;

public class TerminalImpl implements Terminal {
    private final TerminalServer terminalServer;
    private final PinValidator pinValidator;
    private final ShowText showText;
    private Scanner in = new Scanner(System.in);
    private final Timer timer = new Timer();
    private final long DELAY = 10 * 1000;

    private final String initText = "------ Terminal 12213 ------";
    private final String enterLogin = "Enter your login: ";
    private final String enterPin = "Enter your PIN: ";
    private final String enterOperations = "Pick an operation: ";
    private final String errorInputOperation = "Such an operation is not exist!";
    private final String tryAgain = "Try again";
    private final String enterSumDeposit = "Which sum do you want to deposit? (it's necessary multiple of 100)";
    private final String enterSumCash = "Which sum do you want to cash? (it's necessary multiple of 100)";
    private final String errRelationLoginPIN = "Incorrect PIN";
    private final String returnCashToUser = "Take your cash which is ";
    private final String errInputCash = "Such an cash is not valid";
    private final String cashStatement = "Your cash statement is ";
    private final String errIncorrectAttempts = "Too many unsuccessful attempts\nYour account is banned about 10 sec";
    private final String confirmAddMoney = "Your statement was updated on ";
    private final String freeAccount = "Your account is non-locked";

    private final String putMoney = "Put money";
    private final String takeMoney = "Take money";
    private final String checkStatement = "Check the statement";
    private final String exit = "Exit";


    public TerminalImpl(TerminalServer terminalServer, PinValidator pinValidator, ShowText showText) {
        this.terminalServer = terminalServer;
        this.pinValidator = pinValidator;
        this.showText = showText;
    }

    public static void main(String[] args) {
        HashSet<Account> listAccount = new HashSet<>();
        listAccount.add(new Account("alex", "1234", 12000));
        listAccount.add(new Account("harry", "4321", 12200));
        listAccount.add(new Account("red", "1111", 32500));
        TerminalServer terminalServer = new TerminalServer(listAccount);
        PinValidator pinValidator = new PinValidator();
        ShowText showText = new ShowText();

        TerminalImpl terminal = new TerminalImpl(terminalServer, pinValidator, showText);
        terminal.init();
    }

    public void init() {
        showText.showText(initText);

        showText.showText(enterLogin);
        String login = initLogin();

        showText.showText(enterPin);


        int attempt = 1;
        while(true){
            try {
                enterPIN();
                if(terminalServer.validRelationLoginPIN(login, pinValidator.getPIN(), attempt)){
                    break;
                }
                attempt++;
                showText.showText(errRelationLoginPIN);
                showText.showText(tryAgain);
            } catch (AccountIsLockedException e) {
                showText.showText(errIncorrectAttempts);
                wait10Sec(DELAY);
                attempt = 0;
                showText.showText(freeAccount);
            }
        }

        Account account = new Account(login, pinValidator.getPIN());
        initOperations(account);
    }

    public String initLogin() {
        String login = in.nextLine();
        return login;
    }

    //TODO: Out from the method when is ended a delay
    public void wait10Sec(long ms) {
        Date dateStart = new Date();
        long time = dateStart.getTime() + ms;
        Date dateEnd = new Date(time);
        Date dateTemp = new Date();
        while (dateTemp.getTime() - dateEnd.getTime() <= 0) {
            dateTemp = new Date();
            in = new Scanner(System.in);
            if(in.hasNext()){
                dateTemp = new Date();
                showText.showText("Your account is locked about " +
                        (dateEnd.getTime() - dateTemp.getTime())/1000 + " sec.");
            }
        }
    }

    public void enterPIN() {
        while (true) {
            String pin = in.nextLine();
            if (pinValidator.validatePIN(pin)) {
                break;
            }
        }
    }

    public void initOperations(Account account) {
        List<String> operations = new ArrayList<>();
        operations.add(putMoney);
        operations.add(takeMoney);
        operations.add(checkStatement);
        operations.add(exit);
        int numberOperation = 0;
        showText.showText(enterOperations);

        while (true) {
            numberOperation = enterOperations(operations);
            switch (numberOperation) {
                case (1): {
                    showText.showText(enterSumDeposit);
                    int sum = in.nextInt();
                    validSum(sum);
                    putMoney(account.getLogin(), sum);
                    checkStatement(account.getLogin());
                }
                break;
                case (2): {
                    showText.showText(enterSumCash);
                    int sum = in.nextInt();
                    validSum(sum);
                    takeMoney(account.getLogin(), sum);
                    checkStatement(account.getLogin());
                }
                break;
                case (3): {
                    checkStatement(account.getLogin());
                }
                break;
                case (4): {
                    return;
                }
            }
        }
    }

    public int enterOperations(List<String> operations){
        int i = 1;
        for (String oper : operations) {
            System.out.format("[ %d.%s ]", i, oper);
            System.out.print("  ");
            i++;
        }
        System.out.println();
        int numberOperation = 0;
        numberOperation = in.nextInt();
        while (numberOperation > i) {
            showText.showText(errorInputOperation);
            showText.showText(tryAgain);
            numberOperation = in.nextInt();
        }
        return numberOperation;
    }

    public void validSum(int sum) {
        while ((sum % 100 != 0)) {
            showText.showText(errInputCash);
            showText.showText(tryAgain);
            sum = in.nextInt();
        }
    }

    @Override
    public void checkStatement(String login) {
        Integer cash = terminalServer.getStatement(login);
        showText.showText((cashStatement + cash.toString()));
    }

    @Override
    public void putMoney(String login, int value) {
        terminalServer.putMoney(login, value);
        showText.showText((confirmAddMoney + value));
    }

    @Override
    public void takeMoney(String login, int value) {
        Integer cash = terminalServer.takeMoney(login, value);
        showText.showText(returnCashToUser + cash.toString());
    }
}
