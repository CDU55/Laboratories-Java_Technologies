package webServices;

import fileHandling.UserFile;
import interfaces.IFileService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
public class documentsService {

    IFileService service;

    @POST
    @Path("/addDocument")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(UserFile file)
    {
        boolean result=service.addFile(file.getInitialName(),file.getNewName(),file.getPath(),file.getUserId());
        if(result)
        {
            return Response.status(201).entity(file).build();
        }
        else
        {
            return Response.status(400).entity(file).build();
        }
    }

    @DELETE
    @Path("/deleteDocument")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(UserFile file)
    {
        UserFile result=service.deleteFile(file);
        if(result!=null)
        {
            return Response.status(201).entity(result).build();
        }
        else
        {
            return Response.status(400).entity(file).build();
        }
    }

    @PUT
    @Path("/updateFile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UserFile file)
    {
        UserFile result=service.updateFile(file);
        if(result!=null)
        {
            return Response.status(201).entity(result).build();
        }
        else
        {
            return Response.status(400).entity(file).build();
        }
    }

}
