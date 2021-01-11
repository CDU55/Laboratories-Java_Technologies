package authorization;

import annotations.FileName;
import annotations.Logged;
import dataLayer.*;
import annotations.AllFiles;
import fileHandling.FileSaver;
import fileHandling.UserFile;
import interfaces.IUserService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.shaded.commons.io.FilenameUtils;
import producers.DatePeriodProvider;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("controller")
@SessionScoped
public class AppController implements Serializable {

    @Inject
    private User user;
    @Inject
    private IUserService service;
    @Inject
    Event<UserFile> evt;
    @Inject
    @FileName
    Provider<String> nameProvider;

    @Inject
    @AllFiles
    Provider<ArrayList<UserFile>> allFiles;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAllFiles(Provider<ArrayList<UserFile>> allFiles) {
        this.allFiles = allFiles;
    }

    @Logged
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if(!this.service.checkUser(this.user.getUsername(),this.user.getPassword()))
            {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "Invalid username or password"));
                return "";
            }
            else
            {
                this.user.setLoggedOn(true);
                this.user.setRole(service.getRole(this.user.getUsername()));
                this.user.setUserId(service.getId(this.user.getUsername()));
                return "uploadFile";
            }
        } catch (SQLException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  e.getMessage()));
            return "";
        }
    }

    @Logged
    public void register()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if(service.checkUsername(this.user.getUsername()))
            {
                if(service.registerUser(this.user.getUsername(),this.user.getPassword(),"guest"))
                {
                    context.addMessage(null,new FacesMessage("Success","Account created successfully"));
                }
                else
                {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "The registration period is over"));
                }
            }
            else
            {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "This username already exists"));
            }
        } catch (SQLException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  e.getMessage()));
        }
    }

    @Logged
    public List<UserFile> getAllFiles()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.user.getRole()==null || !this.user.getRole().equals("admin"))
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "Only admins can see the submission"));
            return new ArrayList<UserFile>();
        }
        List<UserFile> result=this.allFiles.get();
        if(result==null)
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "An error occurred retrieving the files"));
            return new ArrayList<UserFile>();
        }
        return result;
    }

    @Logged
    public void handleFileUpload(FileUploadEvent event) {
        if(!this.user.isLoggedOn())
        {
            FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE", "Please login before uploading a file" );
            FacesContext.getCurrentInstance().addMessage(null, msg2);
            return;
        }
        if(this.user.getRole()==null || !this.user.getRole().equals("guest"))
        {
            FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE", "Only guests can upload files" );
            FacesContext.getCurrentInstance().addMessage(null, msg2);
            return;
        }
        DatePeriod period = DatePeriodProvider.getPeriod();
        Date currentDate=new Date();
        if(currentDate.after(period.getStartDate()) && currentDate.before(period.getEndDate())) {
            String extension = FilenameUtils.getExtension(event.getFile().getFileName());
            UserFile file = new UserFile();
            file.setInitialName(event.getFile().getFileName());
            String newNameFile = nameProvider.get() + "." + extension;
            file.setPath(FileSaver.defaultPath + "\\" + newNameFile);
            file.setUserId(this.user.getUserId());
            file.setNewName(newNameFile);
            FileSaver.write(file.getPath(), event.getFile().getContent());
            evt.fire(file);
        }
        else
        {
            FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE", "Registration period is over" );
            FacesContext.getCurrentInstance().addMessage(null, msg2);
        }

    }
}
