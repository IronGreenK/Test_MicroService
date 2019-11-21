package MicroTeam.RentaCar.Automovil.controller;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;
import MicroTeam.RentaCar.Automovil.repository.AutomovilRepository;
import MicroTeam.RentaCar.Automovil.service.AutomovilService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping({"/automovil"})
@CrossOrigin(value={})
public class AutomovilController {

    @Autowired
    AutomovilService serv;
    AutomovilRepository rep;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        ResponseEntity<?> response;
        try {
            List<AutomovilEntity> auto = this.serv.getAllAutomovilEntitys();
            response = new ResponseEntity<>(auto, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    @PostMapping("/addAuto")
    public ResponseEntity<?> addAutomovil (@RequestBody AutomovilEntity auto){
        ResponseEntity<?> response;
        String respuestaService = this.serv.saveAutomovilEntity(auto);
        try{
            if(respuestaService.equals("ok")) {
                response = new ResponseEntity<>("{\"Mensaje\":\"Automovil creado creado correctamente\"}", HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>("{\"Error\":\"El automovil es anterior al año 2000\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Hubo un problema\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/getByPatente/{patente}")
    public ResponseEntity<?> getAutomovilByPatente(@PathVariable(value= "patente") String patente){
        ResponseEntity<?> response;
        try{
            AutomovilEntity auto = serv.findAutomovilEntityByPatente(patente);
            response = new ResponseEntity<>(auto, HttpStatus.OK);
        }catch (Exception ex) {
            System.out.println(ex);
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal\"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }


    @GetMapping(value= "/getbyid/{auto-id}")
    public Optional<AutomovilEntity> getById(@PathVariable(value= "auto-id") String id) {
        return serv.findAutomovilEntityById(id);
    }

}
