package RentaCar.Arriendo.msarriendoorq.service;

import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import RentaCar.Arriendo.msarriendoorq.repository.ArriendoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
            LocalDate date = arriendo.getFechaInicio();
            LocalDate sum = date.plusDays(arriendo.getCantDias());
            arriendo.setFechaFin(sum);
            this.repository.save(arriendo);
            return "ok";
    }

    @Override
    public List<ArriendoEntity> getAllArriendoEntitys() {
        return this.repository.findAll();
    }

    @Override
    public ArriendoEntity findArriendoEntityByFolio(String folio) {
        return this.repository.findOneByFolio(folio).get();
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




    public static String sumarAnios(String fechaYHora,int anios) {
        // Crear un formateador como 2018-10-16
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        // Lo convertimos a objeto para poder trabajar con él
        LocalDate fechaLocal = LocalDate.parse(fechaYHora, formateador);

        // Sumar los años indicados
        fechaLocal = fechaLocal.plusDays(anios);

        //Formatear de nuevo y regresar como cadena
        return fechaLocal.format(formateador);
    }
}
