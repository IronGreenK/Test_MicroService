package MicroTeam.RentaCar.Automovil.service;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface AutomovilService {


    String saveAutomovilEntity (AutomovilEntity auto);

    List<AutomovilEntity> getAllAutomovilEntitys();


    Optional<AutomovilEntity> findAutomovilEntityByPatente(String id);

    void deleteAutomovilEntityByPatente(String id);

    void updateAutomovilEntity(AutomovilEntity auto);

    void deleteAllAutomovilEntitys();

}
