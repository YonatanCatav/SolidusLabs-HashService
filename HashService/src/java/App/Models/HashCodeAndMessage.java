package App.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HashCodeAndMessage {
    @Id
    private String hashCode;

    private String message;

    public HashCodeAndMessage()
    {

    }

    public HashCodeAndMessage(String hashCode,String message)
    {
        this.hashCode = hashCode;
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
}
