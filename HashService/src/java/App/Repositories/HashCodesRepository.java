package App.Repositories;


import App.Models.HashCodeAndMessage;
import org.springframework.data.repository.CrudRepository;


public interface HashCodesRepository extends CrudRepository<HashCodeAndMessage,String> {

}
