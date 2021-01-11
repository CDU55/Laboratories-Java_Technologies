package dataLayer;

import fileHandling.UserFile;
import interfaces.IFileService;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.SQLException;

@Named
@Dependent
public class FileAdder {

    @Inject
    IFileService service;

    public void addFile(@Observes UserFile file)
    {
            service.addFile(file.getInitialName(),file.getNewName(),file.getPath(),file.getUserId());
    }
}
