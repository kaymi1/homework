package JDBCFucn;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerDao {
    public static void addAnswerToBD(Answer answer){
        try (PreparedStatement statement = DataSourceHelper.connection().
                prepareStatement("insert into answer (n, result) values (?, ?)")) {
            statement.setInt(1, answer.getN());
            statement.setNString(2, answer.getResult());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
