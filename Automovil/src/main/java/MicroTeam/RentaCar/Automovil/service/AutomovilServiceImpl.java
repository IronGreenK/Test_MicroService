package MicroTeam.RentaCar.Automovil.service;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;

import MicroTeam.RentaCar.Automovil.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AutomovilServiceImpl implements AutomovilService {


    @Autowired
    AutomovilRepository repository;
    public AutomovilServiceImpl() {
    }

    @Override
    public String saveAutomovilEntity(AutomovilEntity auto) {
        if (auto.getAnnoFabricacion()>=2000){
            this.repository.save(auto);
            return "ok";
        } else {
            return "El anno es menor";
        }
    }

    @Override
    public List<AutomovilEntity> getAllAutomovilEntitys() {
        return this.repository.findAll();
    }

    @Override
    public AutomovilEntity findAutomovilEntityByPatente(String patente) {
        return  this.repository.findById(patente).get();
    }

    @Override
    public void deleteAutomovilEntityById(int id) {
        this.repository.deleteById(id);
    }
    @Override
    public void updateAutomovilEntity(AutomovilEntity auto) {
        this.repository.save(auto);
    }
    @Override
    public void deleteAllAutomovilEntitys() {
        this.repository.deleteAll();
    }
}
