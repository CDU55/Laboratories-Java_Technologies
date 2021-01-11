package decorators;

import annotations.Period;
import dataLayer.DatePeriod;
import fileHandling.UserFile;
import interfaces.IFileService;
import producers.DatePeriodProvider;
import webServices.UserFileRequest;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
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
    public UserFile updateFile(UserFileRequest file) {
        return service.updateFile(file);
    }

    @Override
    public UserFile deleteFile(int fileId) {
        return service.deleteFile(fileId);
    }
}
