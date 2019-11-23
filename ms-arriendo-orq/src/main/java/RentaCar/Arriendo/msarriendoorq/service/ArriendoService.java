package RentaCar.Arriendo.msarriendoorq.service;

import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArriendoService {
    String saveArriendoEntity (ArriendoEntity auto);

    List<ArriendoEntity> getAllArriendoEntitys();

    Optional<ArriendoEntity> findArriendoEntityById(String id);

    void deleteArriendoEntityById(String id);

    void updateArriendoEntity(ArriendoEntity auto);

    void deleteAllArriendoEntitys();
}
