
package bf.cdi;

import javax.inject.Named;
import org.springframework.web.context.annotation.RequestScope;

@Named
@RequestScope
public class CdiIndex {

    public CdiIndex() {
    }
    
    private String hashText;

    public String getHashText() {
        return hashText;
    }

    public void setHashText(String hashText) {
        this.hashText = hashText;
    }
    
}
