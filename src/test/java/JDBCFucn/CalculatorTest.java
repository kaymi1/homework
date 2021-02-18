package JDBCFucn;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

//    @BeforeClass
//    public static void setUp() throws SQLException {
//        DataSourceHelper.createDB();
//        Server.createTcpServer().start();
//    }

    @Before
    public void beforeEachTest(){
        try (Statement statement = DataSourceHelper.connection().createStatement()) {
            statement.executeUpdate("truncate table ANSWER");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testFibonachiMethod(){
        List<Integer> result1 = calculator.fibonachi(9);
        System.out.println(result1.toString());
        List<Integer> result2 = calculator.fibonachi(5);
        System.out.println(result2.toString());
        List<Integer> result3 = calculator.fibonachi(3);
        System.out.println(result3.toString());

//        List<Integer> result4 = calculator.fibonachi(13);
//        System.out.println(result4.toString());
    }
}