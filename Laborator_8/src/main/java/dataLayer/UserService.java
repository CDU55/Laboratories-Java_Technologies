package dataLayer;

import interfaces.IUserService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.*;


public class UserService {

   // @Inject
    private  Connection con;
    private  String registerUserStatement="Insert INTO Users Values(?,?,?,?)";
    private  String currentIdSQuery="SELECT MAX(Id) FROM Users";
    private  String checkUserQuery="SELECT COUNT(*) FROM Users WHERE Username=? AND Password=?";
    private  String checkValidUsername="SELECT COUNT(*) FROM Users WHERE Username=?";
    private  String idByUsernameQuery="SELECT Id FROM Users WHERE Username=?";
    private  String roleByUsernameQuery="SELECT Role FROM Users WHERE Username=?";

   // @Override
    public int getNextId() throws SQLException {
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(currentIdSQuery);
        rs.next();
        int nextId=rs.getInt(1)+1;
        rs.close();
        st.close();
        return nextId;
    }

   // @Override
    public boolean checkUser(String username, String password) throws SQLException {
        PreparedStatement st=con.prepareStatement(checkUserQuery);
        st.setString(1,username);
        st.setString(2,password);
        ResultSet rs=st.executeQuery();
        rs.next();
        int usersNumber=rs.getInt(1);
        rs.close();
        st.close();
        if(usersNumber!=0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

   // @Override
    public boolean checkUsername(String username) throws SQLException {
        PreparedStatement st=con.prepareStatement(checkValidUsername);
        st.setString(1,username);
        ResultSet rs=st.executeQuery();
        rs.next();
        int usersNumber=rs.getInt(1);
        rs.close();
        st.close();
        if(usersNumber==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //@Override
    public boolean registerUser(String username, String password, String role) throws SQLException {
        PreparedStatement st=con.prepareStatement(registerUserStatement);
        int nextId=getNextId();
        st.setInt(1,nextId);
        st.setString(2,username);
        st.setString(3,password);
        st.setString(4,role);
        int affectedRows=st.executeUpdate();
        st.close();
        if(affectedRows!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //@Override
    public int getId(String username) throws SQLException {
        PreparedStatement st=con.prepareStatement(idByUsernameQuery);
        st.setString(1,username);
        ResultSet rs=st.executeQuery();
        rs.next();
        int id=rs.getInt(1);
        rs.close();
        st.close();
        return id;
    }

    //@Override
    public String getRole(String username) throws SQLException {
        PreparedStatement st=con.prepareStatement(roleByUsernameQuery);
        st.setString(1,username);
        ResultSet rs=st.executeQuery();
        rs.next();
        String role=rs.getString(1);
        rs.close();
        st.close();
        return role;
    }

}
