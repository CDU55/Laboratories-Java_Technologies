package webServices;

import fileHandling.UserFile;
import interfaces.IFileService;

import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "Documents")
@HandlerChain(file = "handler-chain.xml")
public class getDocumentsService {

    @Inject
    IFileService service;
    @WebMethod
    public List<UserFile> getAll(Integer userId)
    {
        if(userId==null) {
        return service.getAll();
        }
        else
        {
            return service.getByUser(userId);
        }
    }
}
