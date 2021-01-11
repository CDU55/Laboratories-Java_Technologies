package fileHandling;

import dataLayer.UserJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "files")
public class UserFileJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "InitialName",nullable = false)
    private String initialName;

    @Column(name = "Path",nullable = false,unique = true)
    private String path;

    @Column(name = "NewName",nullable = false)
    private String newName;

    @ManyToOne
    @JoinColumn(name = "UserId",referencedColumnName = "Id",nullable = false)
    private UserJPA user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInitialName() {
        return initialName;
    }

    public void setInitialName(String initialName) {
        this.initialName = initialName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public UserJPA getUser() {
        return user;
    }

    public void setUser(UserJPA user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFileJPA that = (UserFileJPA) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(initialName, that.initialName) &&
                Objects.equals(path, that.path) &&
                Objects.equals(newName, that.newName) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initialName, path, newName, user);
    }
}
