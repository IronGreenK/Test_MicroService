package MicroTeam.RentaCar.Automovil.service;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public interface AutomovilService {

    void createAutomovilEntity(List<AutomovilEntity> auto);

    List<AutomovilEntity> getAllAutomovilEntitys();

    Optional<AutomovilEntity> findAutomovilEntityById(int id);

    void deleteAutomovilEntityById(int id);

    void updateAutomovilEntity(AutomovilEntity auto);

    void deleteAllAutomovilEntitys();

}
