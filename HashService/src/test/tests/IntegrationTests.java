import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import src.App.Controllers.MessagesController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import src.App.HashServiceApplication;
import src.App.Repositories.HashCodesRepository;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= HashServiceApplication.class)
@ContextConfiguration("/testsConfiguration.xml")
public class IntegrationTests {

    @Autowired
    HashCodesRepository hashCodesRepository;

    MessagesController messagesController;
    @Before
    public void init()
    {
     ApplicationContext config  = new ClassPathXmlApplicationContext("testsConfiguration.xml");
     messagesController = new MessagesController(config,hashCodesRepository);
    }

    @Test
    public void getMessageByHashCode_beforeHashMessageRequest_ShouldReturnNotFound() {

        //Assign
        Map<String, String> expectedContent = new HashMap<>();
        expectedContent.put("err_msg", "Message not found");

        //Act
        ResponseEntity<Map<String,String>> response= messagesController.getMessage("hello");
        Map<String,String> responseContent = response.getBody();

        //Assert
        assertEquals(responseContent,expectedContent);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void getMessageByHashCode_afterHashMessageRequest_ShouldReturnMessage() {
        //Assign
        Map expectedValue = new HashMap<String, String>();
        expectedValue.put("message","hello World");

        //Act
         String hashCode = messagesController.hashMessage("hello World").get("digest");
         ResponseEntity<Map<String,String>> responseEntity = messagesController.getMessage(hashCode);
         Map responseContent = responseEntity.getBody();

         //Assert
         assertEquals(responseContent,expectedValue);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}
