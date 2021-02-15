package homework16;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Integer> result;

    @Cachable(H2DB.class)
    public List<Integer> fibonachi(int n){

        Cachable cachable = null;
        for(Method method : this.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(Cachable.class)){
                 cachable = method.getAnnotation(Cachable.class);
            }
        }

        if(cachable!= null && cachable.value() == H2DB.class){
            String url = "jdbc:h2:~/test";
            try (Connection connection = DriverManager.getConnection(url)) {
                connection.setAutoCommit(true);
                try (Statement statement = connection.createStatement()) {
                    String sql = "select * from answer where n >= "+ n;
                    try (ResultSet resultSet = statement.executeQuery(sql)) {
                        if(!resultSet.next()){
                            Answer answer = new Answer(n);
                            answer.setResult(getFibonachiNumbers(n));
                            AnswerDao.addAnswerToBD(answer);
                            return result;
                        }
                        System.out.println("Return result from cache");
                        String resultFromDB = resultSet.getString("result");
                        String[] resultSplit = resultFromDB.split(",");
                        List<Integer> resultNumbers = new ArrayList<>();
                        int i = 0;
                        while (i < n){
                            Integer integer = new Integer(resultSplit[i]);
                            resultNumbers.add(integer);
                            i++;
                        }
                        return resultNumbers;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private String getFibonachiNumbers(int n){
        result = new ArrayList<>();
        int n0 = 1;
        int n1 = 1;
        result.add(n0);
        result.add(n1);
        int n2 = 0;
        int i = 2;
        while (i != n){
            n2=n0+n1;
            n0=n1;
            n1=n2;
            result.add(n2);
            i++;
        }
        StringBuilder resultString = new StringBuilder();
        for (Integer integer : result) {
            resultString.append(integer+",");
        }
        return resultString.toString();
    }

    public static void main(String[] args) {
        DataSourceHelper.createDB();

    }
}

class Answer{
    private int number;
    private String result;

    public Answer(int n){
        this.setNumber(n);
    }

    public Answer(int n, String result){
        this.setNumber(n);
        this.setResult(result);
    }

    public int getNumber() {
        return number;
    }

    public String getResult() {
        return result;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
