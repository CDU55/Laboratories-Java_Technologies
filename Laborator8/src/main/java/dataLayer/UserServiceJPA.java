package dataLayer;

import interfaces.IUserService;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Default
public class UserServiceJPA implements IUserService {
    @PersistenceContext(unitName = "documents")
    EntityManager em;
    @Override
    public int getNextId() throws SQLException {
        Query q=em.createQuery("SELECT max (us.id) from UserJPA us");
        Integer currentId=(Integer)q.getSingleResult();
        currentId=currentId!=null?currentId:0;
        return currentId+1;
    }

    @Override
    public boolean checkUser(String username, String password) throws SQLException {
        Query q=em.createQuery("SELECT us from UserJPA us where us.username=:providedUsername and us.password=:providedPassword");
        q.setParameter("providedUsername",username);
        q.setParameter("providedPassword",password);
        List<UserJPA> users=q.getResultList();
        if(users.size()!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean checkUsername(String username) throws SQLException {
        Query q=em.createQuery("SELECT us from UserJPA us where us.username=:providedUsername");
        q.setParameter("providedUsername",username);
        List<UserJPA> users=q.getResultList();
        if(users.size()==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean registerUser(String username, String password, String role) throws SQLException {
        UserJPA user=new UserJPA();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        try {
            em.persist(user);
            em.flush();
            return true;
        }
        catch (Exception e)
        {
            String test=e.getMessage();
            return false;
        }
    }

    @Override
    public int getId(String username) throws SQLException {
        Query q=em.createQuery("SELECT us.id from UserJPA us where us.username=:providedUsername");
        q.setParameter("providedUsername",username);
        List<Integer> ids=q.getResultList();
        return ids.get(0);
    }

    @Override
    public String getRole(String username) throws SQLException {
        Query q=em.createQuery("SELECT us.role from UserJPA us where us.username=:providedUsername");
        q.setParameter("providedUsername",username);
        List<String> roles=q.getResultList();
        return roles.get(0);
    }
}
