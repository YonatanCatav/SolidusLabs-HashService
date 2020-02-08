package src.App.Controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.App.Interfaces.IHash;
import src.App.Models.HashCodeAndMessage;
import src.App.Repositories.HashCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@EnableAutoConfiguration
@RestController
public class MessagesController {

    private ApplicationContext context;
    private IHash hashMethod;

    @Autowired
    HashCodesRepository hashCodesRepository ;

    public MessagesController() {
        context = new ClassPathXmlApplicationContext("app.xml");
        hashMethod = (IHash) context.getBean("hashMethod");
    }
//For integration tests
    public MessagesController(ApplicationContext context, HashCodesRepository hashCodesRepository ) {
        hashMethod = (IHash) context.getBean("hashMethod");
        this.hashCodesRepository =hashCodesRepository;
    }

    @PostMapping(value = "/messages")
    public Map<String, String> hashMessage(@RequestParam(value = "message", defaultValue = "") String message) {
        Map<String, String> response = new HashMap<>();
        if (message == "") {
            response.put("error", "No 'message' was received.\nPlease pass 'message' argument along the request");
        } else {
            String hashedValue = hashMethod.Hash(message);
            hashCodesRepository.save(new HashCodeAndMessage(hashedValue, message));
            response.put("digest", hashedValue);
        }
        return response;
    }

    @RequestMapping(value = "/messages/{hashCode}", method = GET)
    @ResponseBody
    public ResponseEntity<Map<String,String>> getMessage(
            @PathVariable("hashCode") String hashCode) {
        Optional<HashCodeAndMessage> hashCodeAndMessage = hashCodesRepository.findById(hashCode);

        ResponseEntity response;
        if (hashCodeAndMessage.isPresent()) {
            Map responseMessage = new HashMap<String, String>();
            responseMessage = new HashMap<String, String>() {{
                put("message", hashCodeAndMessage.get().getMessage());
            }};
            response = new ResponseEntity(responseMessage, HttpStatus.OK);
        } else {
            Map responseMessage = new HashMap<String, String>();
            responseMessage.put("err_msg", "Message not found");
            response = new ResponseEntity(responseMessage,HttpStatus.NOT_FOUND);
        }

        return response;
    }
}
