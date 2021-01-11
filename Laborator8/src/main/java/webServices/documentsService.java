package webServices;

import fileHandling.UserFile;
import interfaces.IFileService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
public class documentsService {

    @Inject
    IFileService service;

    @POST
    @Path("/addDocument")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(UserFile file)
    {
        UserFileResponse response=new UserFileResponse();
        if(file==null)
        {
            response.setMessage("No document was received");
            return Response.status(405).entity(response).build();
        }
        boolean result=service.addFile(file.getInitialName(),file.getNewName(),file.getPath(),file.getUserId());
        if(result)
        {
            response.setPath(file.getPath());
            response.setInitialName(file.getInitialName());
            response.setNewName(file.getNewName());
            response.setUserId(file.getUserId());
            response.setMessage("Document was successfully added");
            return Response.status(201).entity(response).build();
        }
        else
        {
            response.setMessage("An error occurred while adding the document");
            return Response.status(400).entity(response).build();
        }
    }

    @DELETE
    @Path("/deleteDocument/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int fileId)
    {
        UserFileResponse response=new UserFileResponse();
        UserFile result=service.deleteFile(fileId);
        if(result!=null)
        {
            response.setPath(result.getPath());
            response.setInitialName(result.getInitialName());
            response.setNewName(result.getNewName());
            response.setUserId(result.getUserId());
            response.setMessage("Document was successfully deleted");
            return Response.status(200).entity(response).build();
        }
        else
        {
            response.setMessage("An error occurred while deleting the document");
            return Response.status(400).entity(response).build();
        }
    }

    @PUT
    @Path("/updateFile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UserFileRequest file)
    {
        UserFileResponse response=new UserFileResponse();
        if(file==null)
        {
            response.setMessage("No document was received");
            return Response.status(405).entity(response).build();
        }
        UserFile result=service.updateFile(file);
        if(result!=null)
        {
            response.setPath(result.getPath());
            response.setInitialName(result.getInitialName());
            response.setNewName(result.getNewName());
            response.setUserId(result.getUserId());
            response.setMessage("Document was successfully updated");
            return Response.status(200).entity(response).build();
        }
        else
        {
            response.setMessage("An error occurred while updating the document");
            return Response.status(400).entity(response).build();
        }
    }

}
