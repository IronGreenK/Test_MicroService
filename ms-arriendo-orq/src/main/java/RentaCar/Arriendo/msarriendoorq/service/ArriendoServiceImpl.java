package RentaCar.Arriendo.msarriendoorq.service;

import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import RentaCar.Arriendo.msarriendoorq.repository.ArriendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ArriendoServiceImpl implements ArriendoService{


    @Autowired
    ArriendoRepository repository;

    public ArriendoServiceImpl() {
    }

    @Override
    public String saveArriendoEntity(ArriendoEntity arriendo) {


            this.repository.save(arriendo);
            return "ok";
    }

    @Override
    public List<ArriendoEntity> getAllArriendoEntitys() {
        return this.repository.findAll();
    }

    @Override
    public Optional<ArriendoEntity> findArriendoEntityById(String id) {
        return  this.repository.findById(id);
    }

    @Override
    public void deleteArriendoEntityById(String id) {
        this.repository.deleteById(id);
    }
    @Override
    public void updateArriendoEntity(ArriendoEntity auto) {
        this.repository.save(auto);
    }
    @Override
    public void deleteAllArriendoEntitys() {
        this.repository.deleteAll();
    }
}
