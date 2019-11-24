package MicroTeam.msclienteneg.Controller;

import MicroTeam.msclienteneg.Entity.ClienteEntity;
import MicroTeam.msclienteneg.Service.ClienteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(value = {})
public class ClienteController {
    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping( value = "/getByRut/{cliente}")
    public Optional<ClienteEntity> getByRut(@PathVariable(value = "cliente")String rut){
        return clienteService.buscarClientePorRut(rut);
    }
     @GetMapping("/getAll")
     public ResponseEntity<?>getAll(){
         ResponseEntity<?>response;
         try{
             List<ClienteEntity> cliente = this.clienteService.buscarTodos();
             response = new ResponseEntity<>(cliente, HttpStatus.OK);
         }catch (Exception ex){
             System.out.println(ex);
             response = new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
         return response;
     }

    @PostMapping("/addCliente")
    public ResponseEntity<?>addCliente(@RequestBody ClienteEntity cliente){
        ResponseEntity<?> response;
        String respuService = this.clienteService.createClienteEntity(cliente);
        try{
            if (respuService.equals("Cliente inngresado")){
                response = new ResponseEntity<>("{\"Mensaje\":\"Cliente creado exitosamente\"}", HttpStatus.CREATED);
            }else if(respuService.equals("existe cliente")){
                response = new ResponseEntity<>("{\"Error\":\"Ya existe el cliente\"}",HttpStatus.BAD_REQUEST);
            }else{
                response = new ResponseEntity<>("{\"Error\":\"El Cliente no puede tener menos de 25 años\"}",HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex){
            response = new ResponseEntity<>("{\"Error\":\"Hubo un problema\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping(value = "/delete/{rut}")
    public String delete(@PathVariable(value = "rut") String rut){
        clienteService.deleteClientePorRut(rut);
        return "el cliente con el rut: " + rut + "ha sido eliminado";
    }

    @PutMapping(value = "/update/{rut}")
    public String update(@PathVariable(value = "rut") String rut, @RequestBody ClienteEntity c){
        c.setRut(rut);
        clienteService.updateClienteEntity(c);
        return "El cliente con el rut:" + rut + "fue modificado";
    }

    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {

        clienteService.deleteAllClientesEntitys();
        return "Todos los clientes han sido eliminados.";
    }

}
