package MicroTeam.RentaCar.Automovil.repository;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AutomovilRepository extends MongoRepository<AutomovilEntity, Serializable> {

}
