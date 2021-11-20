package gazelle.gazelleventas.Repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import gazelle.gazelleventas.Models.ClienteModel;



@Repository
public interface ClienteRepository extends MongoRepository<ClienteModel,String> {
    public Optional<ClienteModel> findByUsername(String username);


}
