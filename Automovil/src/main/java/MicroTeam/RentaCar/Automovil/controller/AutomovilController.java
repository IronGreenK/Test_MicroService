package MicroTeam.RentaCar.Automovil.controller;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;
import MicroTeam.RentaCar.Automovil.repository.AutomovilRepository;
import MicroTeam.RentaCar.Automovil.service.AutomovilService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal\"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping(value= "/getbyPatente/{auto}")
    public Optional<AutomovilEntity> getByPatente(@PathVariable(value= "auto")String patente) {
        return serv.findAutomovilEntityByPatente(patente);
    }

    @DeleteMapping(value= "/delete/{auto}")
    public String delete(@PathVariable(value= "auto") String patente) {
        serv.deleteAutomovilEntityByPatente(patente);
        return "El automovil con la patente(" + patente + ") fue eliminado.";
    }

    @PutMapping(value= "/update/{auto}")
    public String update(@PathVariable(value= "auto") String patente, @RequestBody AutomovilEntity a) {
        a.setPatente(patente);
        serv.updateAutomovilEntity(a);
        return "El automovil con la patente(" + patente + ") fue cambiada.";
    }

    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {

        serv.deleteAllAutomovilEntitys();
        return "Todos los automoviles eliminados.";
    }

}
