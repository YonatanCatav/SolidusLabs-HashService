package App.Controllers;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
public class MessagesController {

    @PostMapping(value = "/messages")
    public String getHash(@RequestParam(value = "message", defaultValue = "") String message) {
        return message;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello World Developer!!!";
    }
}
