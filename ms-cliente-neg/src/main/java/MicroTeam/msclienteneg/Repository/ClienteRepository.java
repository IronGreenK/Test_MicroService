package MicroTeam.msclienteneg.Repository;

import MicroTeam.msclienteneg.Entity.ClienteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteEntity, Serializable> {
    Optional<ClienteEntity> findOneByRut(String rut);

    //Optional<List<ClienteEntity>> findByTipo(String tipo);
}
