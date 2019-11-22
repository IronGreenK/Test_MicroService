package RentaCar.Arriendo.msarriendoorq.controller;

import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import RentaCar.Arriendo.msarriendoorq.entity.AutomovilEntity;
import RentaCar.Arriendo.msarriendoorq.repository.ArriendoRepository;
import RentaCar.Arriendo.msarriendoorq.service.ArriendoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping({"/arriendo"})
@CrossOrigin(value={})
public class ArriendoController {

    @Autowired
    ArriendoService serv;
    ArriendoRepository rep;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForObject("http://localhost:8095/automovil/getbyPatente", AutomovilEntity.class);
        ResponseEntity<?> response;
        try {
            List<ArriendoEntity> arriendo = this.serv.getAllArriendoEntitys();
            response = new ResponseEntity<>(arriendo, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal\"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    @PostMapping("/addarriendo")
    public ResponseEntity<?> addArriendo (@RequestBody ArriendoEntity arriendo){
        ResponseEntity<?> response;
        String respuestaService = this.serv.saveArriendoEntity(arriendo);
        try{
            if(respuestaService.equals("ok")) {
                response = new ResponseEntity<>("{\"Mensaje\":\"Arriendo creado creado correctamente\"}", HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>("{\"Error\":\"El Arriendo es anterior al año 2000\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Hubo un problema\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping(value= "/getbyId/{arriendo}")
    public Optional<ArriendoEntity> getById(@PathVariable(value= "arriendo")String Id) {
        return serv.findArriendoEntityById(Id);
    }

    @DeleteMapping(value= "/delete/{arriendo}")
    public String delete(@PathVariable(value= "arriendo") String Id) {
        serv.deleteArriendoEntityById(Id);
        return "El Arriendo con el= " + Id + " fue eliminado.";
    }

    @PutMapping(value= "/update/{arriendo}")
    public String update(@PathVariable(value= "arriendo") String Id, @RequestBody ArriendoEntity a) {
        a.setIdArriendo(Id);
        serv.updateArriendoEntity(a);
        return "El Arriendo con la Id= " + Id + " fue cambiada.";
    }

    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {

        serv.deleteAllArriendoEntitys();
        return "Todos los Arriendoes eliminados.";
    }
}
