package DataBase;

import Model.Users;
import com.mysql.cj.Query;

import java.sql.SQLException;

public class DbUser {
    static boolean active;
        public static Users getUser(String userName) throws SQLException, Exception {
            //JDBC.getConnection();
            String sqlStatement = "SELECT * FROM user WHERE userName = '" + userName + "'";
            Query.makeQuery(sqlStatement);

        }
}
