package utils;
import java.sql.*;
public class DbConnection {

    private static final String dbURL="jdbc:sqlite:C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator2\\src\\Resources\\words.db";
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        Connection conn=DriverManager.getConnection(dbURL);
        return conn;
    }

}
