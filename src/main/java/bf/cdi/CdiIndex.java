package bf.cdi;

import bf.model.HashDataModel;
import com.google.common.hash.Hashing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.annotation.RequestScope;

@Named
@RequestScope
public class CdiIndex {

    public CdiIndex() {
    }

    private String hashText;
    //private Map<String, String> hashes = new HashMap<>();
    private List<HashDataModel> hashesList = new ArrayList<>();
    private HashDataModel hdm;
    
    private String[] TYPES = {
        "md2",
        "md5",
        "sha1",
        "sha256",
        "sha384",
        "sha512",
        "crc32",
        "crc32c",
        "adler32"
    };

    @PostConstruct
    public void afterBirn() {
        hashesList.clear();
    }

    public void calcTextHashesAll() {
        if (!hashText.isEmpty()) {
            hashesList.clear();
            for (String tip: TYPES) {
                hdm=new HashDataModel();
                hdm.setType(tip);
                switch(tip) {
                    case "md2": hdm.setHash(DigestUtils.md2Hex(hashText)); break;
                    case "md5": hdm.setHash(DigestUtils.md5Hex(hashText)); break;
                    case "sha1": hdm.setHash(DigestUtils.sha1Hex(hashText)); break;
                    case "sha256": hdm.setHash(DigestUtils.sha256Hex(hashText)); break;
                    case "sha384": hdm.setHash(DigestUtils.sha384Hex(hashText)); break;
                    case "sha512": hdm.setHash(DigestUtils.sha512Hex(hashText)); break;
                    case "crc32": hdm.setHash(Hashing.crc32().hashUnencodedChars(hashText).toString()); break;
                    case "crc32c": hdm.setHash(Hashing.crc32c().hashUnencodedChars(hashText).toString()); break;
                    case "adler32": hdm.setHash(Hashing.adler32().hashUnencodedChars(hashText).toString()); break;
                }
                hashesList.add(hdm);
            }
            /*hashes.put("md2", DigestUtils.md2Hex(hashText));
            //////////  Hashing.crc32().hashString(buf, Charsets.UTF_8).toString()
            hashes.put("adler32",Hashing.adler32().hashUnencodedChars(hashText).toString());*/
        }
    }
    
    public String getHashText() {
        return hashText;
    }

    public void setHashText(String hashText) {
        this.hashText = hashText;
    }

    public List<HashDataModel> getHashesList() {
        return hashesList;
    }
    
}
