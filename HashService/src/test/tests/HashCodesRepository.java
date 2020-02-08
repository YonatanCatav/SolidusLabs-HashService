import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import src.App.Models.HashCodeAndMessage;

@Repository
public interface HashCodesRepository extends CrudRepository<HashCodeAndMessage,String> {

}
