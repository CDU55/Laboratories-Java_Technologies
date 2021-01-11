package fileHandling;

import java.io.Serializable;

public class UserFile implements Serializable {

    private String initialName;
    private String newName;
    private String path;
    private int userId;

    public UserFile() {

    }

    public String getInitialName() {
        return initialName;
    }

    public void setInitialName(String initialName) {
        this.initialName = initialName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserFile(String initialName, String path, int userId) {
        this.initialName = initialName;
        this.path = path;
        this.userId = userId;
    }
}
