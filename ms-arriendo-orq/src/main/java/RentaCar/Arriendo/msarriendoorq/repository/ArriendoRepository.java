package RentaCar.Arriendo.msarriendoorq.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface ArriendoRepository extends MongoRepository<ArriendoEntity, Serializable> {

    Optional<ArriendoEntity> findOneByFolio(String folio);

}
