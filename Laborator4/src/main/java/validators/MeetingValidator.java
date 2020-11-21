package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.MeetingValidator")
public class MeetingValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        FacesContext context = FacesContext.getCurrentInstance();
        String meetingName=(String)o;
        if(meetingName.equals(""))
        {
            context.addMessage(null, new FacesMessage("Failure",  "Meeting Id cannot be empty, message coming from a custom validator"));

        }
        if(!meetingName.matches("M[0-9]{4}"))
        {
            context.addMessage(null, new FacesMessage("Failure",  "Invalid meeting ID, message coming from a custom validator"));
        }
    }
}
