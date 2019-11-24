package RentaCar.Arriendo.msarriendoorq.controller;

import RentaCar.Arriendo.msarriendoorq.entity.ArriendoEntity;
import RentaCar.Arriendo.msarriendoorq.entity.AutomovilEntity;
import RentaCar.Arriendo.msarriendoorq.entity.ClienteEntity;
import RentaCar.Arriendo.msarriendoorq.entity.ColaboradorEntity;
import RentaCar.Arriendo.msarriendoorq.repository.ArriendoRepository;
import RentaCar.Arriendo.msarriendoorq.service.ArriendoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/arriendo"})
@CrossOrigin(value={})
public class ArriendoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ArriendoService serv;
    ArriendoRepository rep;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
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
        AutomovilEntity auto = restTemplate.getForObject("http://localhost:8090/automovil/getbyPatente/"+arriendo.getPatenteAuto(),AutomovilEntity.class);
        int valor = auto.getValorDiario();
        arriendo.setValorArriendo(valor * arriendo.getCantDias());
        String respuestaService = this.serv.saveArriendoEntity(arriendo);
        System.out.println(respuestaService);
        try{
            if(respuestaService.equals("ok")) {
                System.out.println("entro al ok");
                response = new ResponseEntity<>("{\"Mensaje\":\"Arriendo creado creado correctamente\"}", HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>("{\"Error\":\"El Arriendo es no guardo\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Hubo un problema\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    @GetMapping(value= "/id/{folio}")
    public ResponseEntity<?> findArriendoEntityByFolio(@PathVariable String folio) {

        //
        //System.out.println(colaborador.get());

        ResponseEntity<?> response;
        System.out.println("Arriendo Id = : "+folio);
        try {
            ArriendoEntity arriendo = this.serv.findArriendoEntityByFolio(folio);
            ColaboradorEntity colaborador = restTemplate.getForObject("http://localhost:8096/colaborador/"+arriendo.getRutColaborador(), ColaboradorEntity.class);
            System.out.println("rut colab");
            System.out.println(colaborador.getRut());
            System.out.println("rut colab from arriendo");
            System.out.println(arriendo.getRutColaborador());
            //AutomovilEntity auto = restTemplate.getForObject()
            ClienteEntity cliente = restTemplate.getForObject("http://localhost:8060/cliente/getByRut/"+arriendo.getRutCliente(),
                    ClienteEntity.class);
            System.out.println(cliente.getFecha_vencimiento());
            response = new ResponseEntity<>(arriendo, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
/* (GetByID backUP)
    @GetMapping(value= "/getbyId/{idArriendo}")
    public Optional<ArriendoEntity> getById(@PathVariable(value= "idArriendo")String idArriendo) {
        return serv.findArriendoEntityById(idArriendo);
    }
    */
    @DeleteMapping(value= "/delete/{arriendo}")
    public String delete(@PathVariable(value= "arriendo") String Id) {
        serv.deleteArriendoEntityById(Id);
        return "El Arriendo con el= " + Id + " fue eliminado.";
    }
/*
    @PutMapping(value= "/update/{arriendo}")
    public String update(@PathVariable(value= "arriendo") String Id, @RequestBody ArriendoEntity a) {
        a.setIdArriendo(Id);
        serv.updateArriendoEntity(a);
        return "El Arriendo con la Id= " + Id + " fue cambiada.";
    }
*/
    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {

        serv.deleteAllArriendoEntitys();
        return "Todos los Arriendoes eliminados.";
    }
}
