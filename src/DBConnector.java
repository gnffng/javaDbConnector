import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
    Connection con;
    Statement statement;

    public DBConnector(String src, String dbName, String user, String password){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mariadb://"+src+":3306/"+dbName, user, password);
            statement = con.createStatement();
            System.out.println("DB연결 성공");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }
}
