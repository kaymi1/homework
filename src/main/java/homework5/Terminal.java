package homework5;

public interface Terminal {
    void checkStatement(String login);
    void putMoney(String login, int value);
    void takeMoney(String login, int value);
}
