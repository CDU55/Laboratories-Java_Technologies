import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@ManagedBean(name = "languageSetter")
@SessionScoped
public class LanguageSetter {

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void changeLanguage(String language) {
        String[] info=language.split("_");
        locale = new Locale(info[0],info[1]);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
