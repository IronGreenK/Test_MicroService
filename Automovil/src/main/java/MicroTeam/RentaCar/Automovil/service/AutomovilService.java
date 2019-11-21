package MicroTeam.RentaCar.Automovil.service;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public interface AutomovilService {


    String saveAutomovilEntity (AutomovilEntity auto);

    List<AutomovilEntity> getAllAutomovilEntitys();

    AutomovilEntity findAutomovilEntityByPatente(String patente);

    Optional<AutomovilEntity> findAutomovilEntityById(String id);

    void deleteAutomovilEntityById(int id);

    void updateAutomovilEntity(AutomovilEntity auto);

    void deleteAllAutomovilEntitys();

}
