package fileHandling;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSaver {

    public static String defaultPath="C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator_8\\src\\Files\\";
    public static boolean write(String path,byte[] content)
    {
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
            stream.write(content);
            stream.close();
            FacesMessage msg2 = new FacesMessage("Success", "File successfully uploaded" );
            FacesContext.getCurrentInstance().addMessage(null, msg2);
            return true;
        } catch (FileNotFoundException e) {
            FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE", e.getMessage() );
            FacesContext.getCurrentInstance().addMessage(null, msg2);
            return false;
        } catch (IOException e) {
            FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg2);
            return false;
        }
    }
}
