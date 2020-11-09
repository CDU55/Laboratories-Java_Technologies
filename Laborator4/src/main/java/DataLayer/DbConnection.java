package DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static final String dbURL="jdbc:sqlite:C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator4\\src\\Resources\\meetings.db";
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        Connection conn=DriverManager.getConnection(dbURL);
        Statement stmt=conn.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON;");
        return conn;
    }
}
