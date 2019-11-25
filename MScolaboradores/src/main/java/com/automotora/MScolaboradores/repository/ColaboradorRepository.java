package com.automotora.MScolaboradores.repository;
import com.automotora.MScolaboradores.entity.ColaboradorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends MongoRepository<ColaboradorEntity, Serializable> {

    Optional<ColaboradorEntity> findOneByRut(String rut);
    //Optional<List<ColaboradorEntity>> findByTipo(String tipo);

}
