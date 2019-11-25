package MicroTeam.RentaCar.Automovil.service;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;

import MicroTeam.RentaCar.Automovil.repository.AutomovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AutomovilServiceImpl implements AutomovilService {


    @Autowired
    AutomovilRepository repository;
    public AutomovilServiceImpl() {
    }

    @Override
    public int saveAutomovilEntity(AutomovilEntity auto) {
        if (!findAutomovilEntityByPatente(auto.getPatente()).isPresent()) {
            if (auto.getAnnoFabricacion() >= 2000 && auto.getAnnoFabricacion() <= 2007) {
                if ((isPatternMatcher("[A-Z]{2}[.]{1}[1-9]{1}[0-9]{1}[-]{1}[0-9]{2}", auto.getPatente()))) {
                    auto.setEstado("Libre");
                    this.repository.save(auto);
                    //Funciona con formato 1
                    return 1;
                } else {
                    //No funciona el formato pedido
                    return 3;
                }
            } else if (auto.getAnnoFabricacion() > 2007) {
                if (isPatternMatcher("[BCDFGHJKLPRSTVWXYZ]{2}[-]{1}[BCDFGHJKLPRSTVWXYZ]{2}[.]{1}[0-9]{2}", auto.getPatente())) {
                    auto.setEstado("Libre");
                    this.repository.save(auto);
                    //Funciona con formato 2 es menor a 2007
                    return 2;
                } else {
                    //No funciona por el formato pedido
                    return 4;
                }
            } else {
                // NO funciona la fecha es menor a 2000
                return 5;
            }
        }
        return 6;
    }

    @Override
    public List<AutomovilEntity> getAllAutomovilEntitys() {
        return this.repository.findAll();
    }

    @Override
    public Optional<AutomovilEntity> findAutomovilEntityByPatente(String patente) {
        return  this.repository.findOneByPatente(patente);
    }

    @Override
    public void deleteAutomovilEntityByPatente(String patente) {
        this.repository.deleteById(patente);
    }
    @Override
    public void updateAutomovilEntity(AutomovilEntity auto) {
        this.repository.save(auto);
    }
    @Override
    public void deleteAllAutomovilEntitys() {
        this.repository.deleteAll();
    }

    private boolean isPatternMatcher(String exp, String val) throws NullPointerException {
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(val);
        return m.matches();
    }


}
