package interfaces;

import fileHandling.UserFile;
import webServices.UserFileRequest;
import webServices.UserFileResponse;

import java.io.Serializable;
import java.util.ArrayList;

public interface IFileService extends Serializable {
    int getNextId();

    String getNexFileName();

    boolean addFile(String oldName, String newName, String path, int userId);

    ArrayList<UserFile> getAll();
    ArrayList<UserFile> getByUser(Integer userId);

    UserFile updateFile(UserFileRequest file);
    UserFile deleteFile(int fileId);
}
