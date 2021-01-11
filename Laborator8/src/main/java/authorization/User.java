package authorization;

import com.sun.istack.internal.NotNull;

import javax.enterprise.inject.Default;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Default
public class User implements Serializable {

    @NotNull
    @Size(min=5, max=20,message = "The username length should be between 5 and 20")
    @Pattern(message = "The username should only contain alphanumeric character",regexp = "[a-zA-Z0-9]*")
    private String username;
    @NotNull
    @Size(min=5, max=15,message = "The username length should be between 4 and 15")
    @Pattern(message = "The password should only contain alphanumeric character",regexp = "[a-zA-Z0-9]*")
    private String password;
    private String role;
    private int userId;
    private boolean isLoggedOn;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLoggedOn() {
        return isLoggedOn;
    }

    public void setLoggedOn(boolean loggedOn) {
        isLoggedOn = loggedOn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
