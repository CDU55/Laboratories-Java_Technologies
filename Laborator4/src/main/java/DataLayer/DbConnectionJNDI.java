package DataLayer;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class DbConnectionJNDI {
    public  static DataSource dataSource;

    public static Connection getConnection() throws NamingException, SQLException {
        if(dataSource==null) {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/MeetingsDB");
        }
           return dataSource.getConnection();
    }
}
