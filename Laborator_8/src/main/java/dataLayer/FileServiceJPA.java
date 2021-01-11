package dataLayer;

import authorization.User;
import fileHandling.UserFile;
import fileHandling.UserFileJPA;
import interfaces.IFileService;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class FileServiceJPA implements IFileService {
    @PersistenceContext(unitName = "documents")
    EntityManager em;
    @Override
    public int getNextId() {
        Query q=em.createQuery("SELECT max (f.id) from UserFileJPA f");
        Integer currentId=(Integer)q.getSingleResult();
        currentId=currentId!=null?currentId:0;
        return currentId+1;
    }

    @Override
    public String getNexFileName() {
        int nextId=getNextId();
        String nextName="Submission_"+nextId;
        return nextName;
    }

    @Override
    @Transactional
    public boolean addFile(String oldName, String newName, String path, int userId) {
        Query q=em.createQuery("SELECT us FROM UserJPA us where us.id=:providedUserId");
        q.setParameter("providedUserId",userId);
        List<UserJPA> users=q.getResultList();
        if(users.size()!=1)
        {
            return false;
        }
        UserJPA user=users.get(0);
        UserFileJPA file=new UserFileJPA();
        file.setUser(user);
        file.setPath(path);
        file.setInitialName(oldName);
        file.setNewName(newName);
        try
        {
            em.persist(file);
            em.flush();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public ArrayList<UserFile> getAll()  {
        Query q=em.createQuery("SELECT f FROM UserFileJPA f");
        List<UserFileJPA> files=q.getResultList();
        ArrayList<UserFile> filesResult=new ArrayList<UserFile>();
        for(UserFileJPA file:files)
        {
            UserFile resultFile=new UserFile();
            resultFile.setUserId(file.getUser().getId());
            resultFile.setPath(file.getPath());
            resultFile.setInitialName(file.getInitialName());
            resultFile.setNewName(file.getNewName());
            filesResult.add(resultFile);
        }
        return filesResult;
    }

    @Override
    public ArrayList<UserFile> getByUser(Integer userId) {
        Query q=em.createQuery("SELECT f FROM UserFileJPA f where f.user.id=:providedUserId");
        q.setParameter("providedUserId",userId);
        List<UserFileJPA> files=q.getResultList();
        ArrayList<UserFile> filesResult=new ArrayList<UserFile>();
        for(UserFileJPA file:files)
        {
            UserFile resultFile=new UserFile();
            resultFile.setUserId(file.getUser().getId());
            resultFile.setPath(file.getPath());
            resultFile.setInitialName(file.getInitialName());
            resultFile.setNewName(file.getNewName());
            filesResult.add(resultFile);
        }
        return filesResult;
    }

    @Override
    public UserFile updateFile(UserFile file) {
        Query q=em.createQuery("SELECT f from UserFileJPA f where f.id=:providedId");
        q.setParameter("providedId",file.getUserId());
        List<UserFileJPA> files=q.getResultList();
        if(files.size()!=1)
        {
            return null;
        }
        else
        {
            UserFileJPA oldFile=files.get(0);
            oldFile.setInitialName(file.getInitialName());
            oldFile.setNewName(file.getNewName());
            oldFile.setPath(file.getPath());
            Query q2=em.createQuery("SELECT us FROM UserJPA us where us.id=:providedUserId");
            q2.setParameter("providedUserId",file.getUserId());
            List<UserJPA> users=q2.getResultList();
            if(users.size()!=1)
            {
                return null;
            }
            oldFile.setUser(users.get(0));
            try
            {
                em.merge(oldFile);
                em.flush();
                return file;
            }
            catch (Exception e)
            {
                return null;
            }
        }
    }

    @Override
    public UserFile deleteFile(UserFile file) {
        Query q=em.createQuery("SELECT f from UserFileJPA f where f.id=:providedId");
        q.setParameter("providedId",file.getUserId());
        List<UserFileJPA> files=q.getResultList();
        if(files.size()!=1)
        {
            return null;
        }
        else
        {
            UserFileJPA deleteFile=files.get(0);
            try
            {
                em.detach(deleteFile);
                em.flush();
                return file;
            }
            catch(Exception e)
            {
                return null;
            }
        }
    }
}
