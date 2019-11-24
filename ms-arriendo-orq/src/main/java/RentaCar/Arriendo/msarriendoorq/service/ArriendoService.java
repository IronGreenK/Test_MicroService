package RentaCar.Arriendo.msarriendoorq.service;

import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArriendoService {
    String saveArriendoEntity (ArriendoEntity auto);

    List<ArriendoEntity> getAllArriendoEntitys();

    ArriendoEntity findArriendoEntityByFolio(String folio);

    void deleteArriendoEntityById(String id);

    void updateArriendoEntity(ArriendoEntity auto);

    void deleteAllArriendoEntitys();
}
