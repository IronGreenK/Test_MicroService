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
    AutomovilRepository dao;
    public AutomovilServiceImpl() {
    }
    @Override
    public void createAutomovilEntity(List<AutomovilEntity> auto) {
        this.dao.saveAll(auto);
    }
    @Override
    public List<AutomovilEntity> getAllAutomovilEntitys() {
        return this.dao.findAll();
    }
    @Override
    public Optional<AutomovilEntity> findAutomovilEntityById(int id) {
        return this.dao.findById(id);
    }
    @Override
    public void deleteAutomovilEntityById(int id) {
        this.dao.deleteById(id);
    }
    @Override
    public void updateAutomovilEntity(AutomovilEntity auto) {
        this.dao.save(auto);
    }
    @Override
    public void deleteAllAutomovilEntitys() {
        this.dao.deleteAll();
    }
}
