package bf.cdi;

import bf.model.HashDataModel;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.annotation.RequestScope;

@Named
@RequestScope
public class CdiIndex {

    public CdiIndex() {
    }
    
    @Inject 
    private CdiMessages cdiMess;        

    private String hashText;
    //private Map<String, String> hashes = new HashMap<>();
    private List<HashDataModel> hashesList = new ArrayList<>();
    private HashDataModel hdm;
    private UploadedFile file;
    
    private String[] TYPES = {
        "md2",
        "md5",
        "sha1",
        "sha256",
        "sha384",
        "sha512",
        /////////
        "crc32",
        "crc32c",
        "adler32",
        "farmHashFingerprint64",
        "murmur3_128",
        "murmur3_32",
        "sipHash24"
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
                    case "md5": hdm.setHash(Hashing.md5().hashString(hashText, Charsets.UTF_8).toString()); break;
                    //case "md5": hdm.setHash(DigestUtils.md5Hex(hashText)); break;
                    case "sha1": hdm.setHash(DigestUtils.sha1Hex(hashText)); break;
                    case "sha256": hdm.setHash(DigestUtils.sha256Hex(hashText)); break;
                    case "sha384": hdm.setHash(DigestUtils.sha384Hex(hashText)); break;
                    case "sha512": hdm.setHash(DigestUtils.sha512Hex(hashText)); break;
                    //case "crc32": hdm.setHash(Hex.encodeHexString(Hashing.crc32().hashUnencodedChars(hashText).toString().getBytes())); break;
                    case "crc32": hdm.setHash(Hashing.crc32().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "crc32c": hdm.setHash(Hashing.crc32c().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "adler32": hdm.setHash(Hashing.adler32().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "farmHashFingerprint64": hdm.setHash(Hashing.farmHashFingerprint64().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "murmur3_128": hdm.setHash(Hashing.murmur3_128().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "murmur3_32": hdm.setHash(Hashing.murmur3_32().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "sipHash24": hdm.setHash(Hashing.sipHash24().hashString(hashText, Charsets.UTF_8).toString()); break;
                }
                hashesList.add(hdm);
            }
            /*hashes.put("md2", DigestUtils.md2Hex(hashText));
            //////////  Hashing.crc32().hashString(buf, Charsets.UTF_8).toString()
            hashes.put("adler32",Hashing.adler32().hashUnencodedChars(hashText).toString());*/
            cdiMess.addMessage("Hashes calculated !","calc-hash",FacesMessage.SEVERITY_INFO);
        } else {cdiMess.addMessage("Emrty Text Area !","null-text",FacesMessage.SEVERITY_INFO);}
    }
    
    //@Async
    public void upload() {
        if (file.getSize()<=0L) {
            cdiMess.addMessage("Emrty file !","null-file",FacesMessage.SEVERITY_INFO);
            return;
        }
        try (InputStream is = file.getInputstream()){
            hashesList.clear();
            for (String tip: TYPES) {
                hdm=new HashDataModel();
                hdm.setType(tip);
                switch(tip) {
                    case "md2": hdm.setHash(DigestUtils.md2Hex(is)); break;
                    case "md5": hdm.setHash(DigestUtils.md5Hex(is)); break;
                    case "sha1": hdm.setHash(DigestUtils.sha1Hex(is)); break;
                    case "sha256": hdm.setHash(DigestUtils.sha256Hex(is)); break;
                    case "sha384": hdm.setHash(DigestUtils.sha384Hex(is)); break;
                    case "sha512": hdm.setHash(DigestUtils.sha512Hex(is)); break;
                    //case "crc32": hdm.setHash(Hex.encodeHexString(Hashing.crc32().hashUnencodedChars(hashText).toString().getBytes())); break;
                    /*case "crc32": hdm.setHash(com.google.common.io.Files..asByteSource(file).hash(Hashing.crc32()).toString()); break;
                    case "crc32c": hdm.setHash(com.google.common.io.Files.hash(file, Hashing.crc32c()).toString()); break;
                    case "adler32": hdm.setHash(Hashing.adler32().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "farmHashFingerprint64": hdm.setHash(Hashing.farmHashFingerprint64().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "murmur3_128": hdm.setHash(Hashing.murmur3_128().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "murmur3_32": hdm.setHash(Hashing.murmur3_32().hashString(hashText, Charsets.UTF_8).toString()); break;
                    case "sipHash24": hdm.setHash(Hashing.sipHash24().hashString(hashText, Charsets.UTF_8).toString()); break;*/
                }
                hashesList.add(hdm);
            }
        }  catch (IOException ex) {}
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
}
