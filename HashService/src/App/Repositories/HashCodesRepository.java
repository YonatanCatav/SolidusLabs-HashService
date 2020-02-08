package src.App.Repositories;


import src.App.Models.HashCodeAndMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashCodesRepository extends CrudRepository<HashCodeAndMessage,String> {

}
