import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import src.App.Controllers.MessagesController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import src.App.HashServiceApplication;
import src.App.Repositories.HashCodesRepository;

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
        //Act
        String response = messagesController.getMessage("hello").get("err_msg");
        //Assert
        assertEquals(response,"Message not found");
    }

    @Test
    public void getMessageByHashCode_afterHashMessageRequest_ShouldReturnMessage() {
        //Act
         String hashCode = messagesController.hashMessage("hello World").get("digest");
         String response = messagesController.getMessage(hashCode).get("message");

         //Assert
         assertEquals(response,"hello World");
    }

}
