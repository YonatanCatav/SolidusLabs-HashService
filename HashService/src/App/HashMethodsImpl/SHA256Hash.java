package src.App.HashMethodsImpl;

import src.App.Interfaces.IHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;

public class SHA256Hash implements IHash {
    private MessageDigest m_messageDigest;
    public SHA256Hash()
    {
        try {
            m_messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            Logger logger = LoggerFactory.getLogger(SHA256Hash.class);
            logger.error(e.getMessage());
        }
    }
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String Hash(String content)
    {
        byte[] bytes = m_messageDigest.digest(content.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(bytes);
    }
}
