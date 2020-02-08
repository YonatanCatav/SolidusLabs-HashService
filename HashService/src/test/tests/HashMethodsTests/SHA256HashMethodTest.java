package HashMethodsTests;

import org.junit.Test;
import src.App.HashMethodsImpl.SHA256Hash;

import static junit.framework.TestCase.assertEquals;

public class SHA256HashMethodTest {

    @Test
    public void validateKnownSHA256HashValues()
    {
        SHA256Hash sha256Hash = new SHA256Hash();
        String hashedValue = sha256Hash.Hash("my name is andrey");
        String hashedValue2 = sha256Hash.Hash("");
        assertEquals(hashedValue,"4e49462654ca1098aa363adb8594de0b004a4b9f78382c60c8374923d8e9ae56");
        assertEquals(hashedValue2,"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
    }
}
