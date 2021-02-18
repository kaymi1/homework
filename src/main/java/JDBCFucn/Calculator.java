package JDBCFucn;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Calculator {
    private List<Integer> result;
    private DataSourceHelper dataSourceHelper;

    @PostConstruct
    public void init(){
        try (Statement statement = dataSourceHelper.connection().createStatement()) {
            statement.executeUpdate("truncate table ANSWER");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Cachable(H2DB.class)
    public List<Integer> fibonachi(int n){

        for(Method method : this.getClass().getDeclaredMethods()){
            Cachable cachable = method.getAnnotation(Cachable.class);
            if(cachable != null && method.getName().equals("fibonachi")){
                if(cachable.value() == H2DB.class){
                    return returnResultFromDBIfExistOrCalculate(n);
                }
            }
        }
        return new ArrayList<>();
    }

    private List<Integer> returnResultFromDBIfExistOrCalculate(int n){
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
                    System.out.println();
                    System.out.println("----------- Result from DB -----------");
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
}

