package interfaces;

import java.io.Serializable;
import java.sql.SQLException;

public interface IUserService extends Serializable {
    int getNextId() throws SQLException;

    boolean checkUser(String username, String password) throws SQLException;

    boolean checkUsername(String username) throws SQLException;

    boolean registerUser(String username, String password, String role) throws SQLException;

    int getId(String username) throws SQLException;
    String getRole(String username) throws SQLException;


}
