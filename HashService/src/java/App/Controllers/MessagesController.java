package App.Controllers;
import App.HashMethod.IHash;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;


@EnableAutoConfiguration
@RestController
public class MessagesController {

    private  ApplicationContext context;
    private IHash hashMethod;
    public MessagesController()
    {
        context = new ClassPathXmlApplicationContext("app.xml");
        hashMethod = (IHash)context.getBean("hashMethod");
    }
    @PostMapping(value = "/messages")
    public Map<String,String> getHash(@RequestParam(value = "message", defaultValue = "") String message) {
        Map<String,String> result = new HashMap<>();
        if(message=="")
        {
            result.put("error","No 'message' was received.\nPlease pass 'message' argument along the request");
        }
        else
        {
            String hashedValue = hashMethod.Hash(message);
            result.put("digest",hashedValue);
        }
        return result;
    }
}
