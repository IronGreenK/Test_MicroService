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
        int respuestaService = this.serv.saveAutomovilEntity(auto);
        try{

                switch (respuestaService) {
                    case 1:
                        response = new ResponseEntity<>("{\"Mensaje\":\"Automovil creado creado correctamente patente 1\"}", HttpStatus.CREATED);
                        break;
                    case 2:
                        response = new ResponseEntity<>("{\"Mensaje\":\"Automovil creado creado correctamente patente 2\"}", HttpStatus.CREATED);
                        break;
                    case 3:
                        response = new ResponseEntity<>("{\"Error\":\"El automovil tiene error formato 1\"}",HttpStatus.BAD_REQUEST);
                        break;
                    case 4:
                        response = new ResponseEntity<>("{\"Error\":\"El automovil tiene error formato 2\"}",HttpStatus.BAD_REQUEST);
                        break;
                    case 5:
                        response = new ResponseEntity<>("{\"Error\":\"El automovil es anterior al año 2000\"}",HttpStatus.BAD_REQUEST);
                        break;
                    case 6:
                        response = new ResponseEntity<>("{\"Error\":\"La patente existe\"}",HttpStatus.BAD_REQUEST);
                        break;
                    default:
                        response = new ResponseEntity<>("{\"Error\":\"No encontro el numero requerido\"}",HttpStatus.BAD_REQUEST);
                        break;
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
