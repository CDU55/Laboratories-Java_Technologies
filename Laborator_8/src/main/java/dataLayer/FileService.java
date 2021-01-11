package dataLayer;

import fileHandling.UserFile;
import interfaces.IFileService;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;

public class FileService {

    @Inject
    private Connection con;
    private  String addFileStatement="Insert INTO Files Values(?,?,?,?,?)";
    private  String currentIdSQuery="SELECT MAX(Id) FROM Files";
    private String getAll="SELECT * FROM Files ";

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

    //@Override
    public String getNexFileName() throws SQLException {
        int nextId=getNextId();
        String nextName="Submission_"+nextId;
        return nextName;
    }

   // @Override
    public ArrayList<UserFile> getAll() throws SQLException {
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(getAll);
        ArrayList<UserFile> result=new ArrayList<UserFile>();
        while (rs.next())
        {
            UserFile file=new UserFile();
            file.setInitialName(rs.getString(1));
            file.setNewName(rs.getString(2));
            file.setPath(rs.getString(3));
            file.setUserId(rs.getInt(4));
            result.add(file);
        }
        rs.close();
        st.close();
        return result;
    }

  //  @Override
    public boolean addFile(String oldName, String newName, String path, int userId) throws SQLException {
        PreparedStatement st=con.prepareStatement(addFileStatement);
        int nextId=getNextId();
        st.setInt(1,nextId);
        st.setString(2,oldName);
        st.setString(3,newName);
        st.setString(4,path);
        st.setInt(5,userId);
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
}
