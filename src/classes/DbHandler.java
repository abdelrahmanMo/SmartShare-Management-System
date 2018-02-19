package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbHandler {
    protected Connection dbconnection;
    public Connection getConnection() {
        String host = "localhost";
        String port = "3306";
        String dbname = "mproject";
        String user = "root";
        String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
        String password = "";
        final String ConnectionString = "jdbc:mysql://" + host + ":" + port + "/" + dbname + unicode;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
              System.err.println("error in database class");
            System.err.println(e.getMessage());
        }
        try {
            
            dbconnection = DriverManager.getConnection(ConnectionString, user, password);

        } catch (SQLException e) {
            System.err.println("error in database class");
            System.err.println(e.getMessage());
            
        }
        return dbconnection;
    }

}
