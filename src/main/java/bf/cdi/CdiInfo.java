
package bf.cdi;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import org.omnifaces.util.Faces;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Named
//@RequestScope
@Scope(value = WebApplicationContext.SCOPE_REQUEST, 
       proxyMode = ScopedProxyMode.DEFAULT)
public class CdiInfo {

    public CdiInfo() {
    }
    
    @Inject
    private HttpServletRequest hsrI;
    @Inject
    private FacesContext fcI;
    @Inject
    private ExternalContext ecI;
    
    private HttpSession sessionI; 
    
    private HttpServletRequest hsr;
    private FacesContext fc;
    private ExternalContext ec;
    private HttpSession session;
    
    @PostConstruct
    public void init() {
        fc=FacesContext.getCurrentInstance();
        ec=fc.getExternalContext();
        session=(HttpSession)ec.getSession(true);
        hsr=(HttpServletRequest)ec.getRequest();
        sessionI=hsrI.getSession(true); 
        //Faces.
    }

    public ExternalContext getEc() {
        return ec;
    }

    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public HttpServletRequest getHsr() {
        return hsr;
    }

    public void setHsr(HttpServletRequest hsr) {
        this.hsr = hsr;
    }

    public HttpServletRequest getHsrI() {
        return hsrI;
    }

    public void setHsrI(HttpServletRequest hsrI) {
        this.hsrI = hsrI;
    }

    public HttpSession getSessionI() {
        return sessionI;
    }

    public void setSessionI(HttpSession sessionI) {
        this.sessionI = sessionI;
    }

    public FacesContext getFcI() {
        return fcI;
    }

    public void setFcI(FacesContext fcI) {
        this.fcI = fcI;
    }

    public ExternalContext getEcI() {
        return ecI;
    }

    public void setEcI(ExternalContext ecI) {
        this.ecI = ecI;
    }

}
