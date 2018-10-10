package bf.cdi;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.web.context.annotation.SessionScope;

@Named("userSession") 
@SessionScope
public class UserSession { //implements Serializable {
    
    @Inject 
    private CdiMessages cdiMess;    
    
    private String skin;
    private String lang; 
    
    private final String [] LANGS = {
        "ru",
        "en"
    }; 
    
    private final String [] SKINS = {
        "afterdark", 
        "afternoon", 
        "afterwork", 
        "aristo", 
        "black-tie", 
        "blitzer", 
        "bluesky", 
        "bootstrap", 
        "casablanca", 
        "cupertino", 
        "cruze", 
        "dark-hive", 
        "delta", 
        "dot-luv", 
        "eggplant", 
        "excite-bike", 
        "flick", 
        "glass-x", 
        "home", 
        "hot-sneaks", 
        "humanity", 
        "le-frog", 
        "midnight", 
        "mint-choc", 
        "omega", 
        "overcast", 
        "pepper-grinder", 
        "redmond", 
        "rocket", 
        "sam", 
        "smoothness", 
        "south-street", 
        "start", 
        "sunny", 
        "swanky-purse", 
        "trontastic", 
        "ui-darkness", 
        "ui-lightness", 
        "vader"
    };
    
    @PostConstruct
    public void afterBirn() {
        if (skin==null) skin = "hot-sneaks";
        lang = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().toString();
        if (!lang.equals("ru") && !lang.equals("en")) lang = "en";
        
    }
    
    public void changeLang (String lang) {
        lang=lang.trim();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getViewRoot().setLocale(new Locale(lang));
        //cdiMess.addMessage("info","","lang_changed"," "+fc.getViewRoot().getLocale().toString(),this.getLang());
        //cdiMess.addMessage("info","Lang set to "+fc.getViewRoot().getLocale().toString(),"",FacesMessage.SEVERITY_INFO); 
        //cdiMess.addMessage("ejb Lang =  "+userSession.getLang(),"info-lang-change",FacesMessage.SEVERITY_INFO); 
    }
    
    public void changeLangListener (ValueChangeEvent evt) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getViewRoot().setLocale(new Locale(evt.getNewValue().toString()));
    }        

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
        //ejbConfig.setSkin(sskin);
        cdiMess.addMessage("Skin set to "+skin,"skin-set",FacesMessage.SEVERITY_INFO); 
        //cdiMess.addMessage("info","","skin_changed"," "+this.skin,this.getLang());
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String[] getLANGS() {
        return LANGS;
    }
    
    public String[] getSKINS() {
        return SKINS;
    }        
    
}
