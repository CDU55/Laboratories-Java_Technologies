package dataLayer;

import javax.enterprise.inject.Produces;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static final String dbURL="jdbc:sqlite:C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator_8\\src\\Resources\\files.db";
    @Produces
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        Connection conn=DriverManager.getConnection(dbURL);
        Statement stmt=conn.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON;");
        return conn;
    }
}
