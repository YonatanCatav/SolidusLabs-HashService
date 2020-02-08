package App.Hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;

public class Hasher {
    private MessageDigest m_messageDigest;
    public Hasher()
    {
        try {
            m_messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            Logger logger = LoggerFactory.getLogger(Hasher.class);
            logger.error(e.getMessage());
        }
    }
    public String Hash(String content)
    {
        byte[] bytes = m_messageDigest.digest(content.getBytes());
        String hashedString = new String(bytes, StandardCharsets.UTF_8);
        return hashedString;
    }
}
