
package bf.cdi;

import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;

@Named(value = "cdiMessages")  // @Named=@Component and @Named=@Qualifier(spring)
@Scope("prototype")
public class CdiMessages {

    public CdiMessages() {    }
    
    public void addMessage(String summary, String detail, Severity sev) {
        FacesMessage message = new FacesMessage(sev, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }      
    
}
