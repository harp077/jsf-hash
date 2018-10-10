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
    private Map<String, String> hashes = new HashMap<>();
    private List<HashDataModel> hashesList = new ArrayList<>();
    private HashDataModel hdm;

    @PostConstruct
    public void afterBirn() {
        hashes.clear();
        hashesList.clear();
    }

    public String getHashText() {
        return hashText;
    }

    public void setHashText(String hashText) {
        this.hashText = hashText;
    }

    public Map<String, String> getHashes() {
        return hashes;
    }

    public void calcTextHashesAll() {
        if (!hashText.isEmpty()) {
            hashes.clear();
            hashesList.clear();
            hdm=new HashDataModel();
            hdm.setType("md2");
            hdm.setHash(DigestUtils.md2Hex(hashText));
            hashesList.add(hdm);
            hdm=new HashDataModel();
            hdm.setType("md5");
            hdm.setHash(DigestUtils.md5Hex(hashText));
            hashesList.add(hdm);            
            hashes.put("md2", DigestUtils.md2Hex(hashText));
            hashes.put("md5", DigestUtils.md5Hex(hashText));
            hashes.put("sha1", DigestUtils.sha1Hex(hashText));
            hashes.put("sha256", DigestUtils.sha256Hex(hashText));
            hashes.put("sha384", DigestUtils.sha384Hex(hashText));
            hashes.put("sha512", DigestUtils.sha512Hex(hashText));
            //////////  Hashing.crc32().hashString(buf, Charsets.UTF_8).toString()
            hashes.put("crc32",  Hashing.crc32().hashUnencodedChars(hashText).toString());
            hashes.put("crc32c", Hashing.crc32c().hashUnencodedChars(hashText).toString());
            hashes.put("adler32",Hashing.adler32().hashUnencodedChars(hashText).toString());

        }

    }

    public List<HashDataModel> getHashesList() {
        return hashesList;
    }
    
}
