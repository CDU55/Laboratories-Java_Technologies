package webServices;

import fileHandling.UserFile;
import interfaces.IFileService;
import interfaces.IUserService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "Documents")
public class getDocumentsService {

    @Inject
    IFileService service;
    @WebMethod
    public List<UserFile> getAll()
    {
        return service.getAll();
    }
}
