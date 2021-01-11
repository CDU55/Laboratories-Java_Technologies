package decorators;

import dataLayer.DatePeriod;
import producers.DatePeriodProvider;
import interfaces.IFileService;
import annotations.Period;
import fileHandling.UserFile;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Decorator
public class FileServiceDecorator implements IFileService {
    @Inject
    @Delegate
    @Default
    IFileService service;
    @Inject @Period
    DatePeriod period;
    @Override
    public int getNextId()  {
        return service.getNextId();
    }

    @Override
    public String getNexFileName()  {
        return service.getNexFileName();
    }

    @Override
    public boolean addFile(String oldName, String newName, String path, int userId) {
        Date currentDate=new Date();
        this.period= DatePeriodProvider.getPeriod();
        if(currentDate.after(period.getStartDate()) && currentDate.before(period.getEndDate()))
        {
            service.addFile(oldName,newName,path,userId);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public ArrayList<UserFile> getAll()  {
        return service.getAll();
    }

    @Override
    public ArrayList<UserFile> getByUser(Integer userId) {
        return service.getByUser(userId);
    }

    @Override
    public UserFile updateFile(UserFile file) {
        return service.updateFile(file);
    }

    @Override
    public UserFile deleteFile(UserFile file) {
        return service.deleteFile(file);
    }
}
