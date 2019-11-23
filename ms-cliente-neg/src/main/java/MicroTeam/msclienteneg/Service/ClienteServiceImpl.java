package MicroTeam.msclienteneg.Service;

import MicroTeam.msclienteneg.Entity.ClienteEntity;
import MicroTeam.msclienteneg.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public String createClienteEntity(ClienteEntity cliente) {
        if (cliente.getEdad()>=25){
          this.clienteRepository.save(cliente);
          return "ok";
        }else{
            return " no puede ser menor a 25 años";
        }
    }

    @Override
    public Optional<ClienteEntity> buscarClientePorRut(String rut) {
        return this.clienteRepository.findOneByRut(rut);
    }


    @Override
    public List<ClienteEntity> buscarTodos() {

        return this.clienteRepository.findAll();
    }

    @Override
    public void updateClienteEntity(ClienteEntity cliente) {
         this.clienteRepository.save(cliente);
    }

    @Override
    public void deleteClientePorRut(String rut) {

        this.clienteRepository.deleteById(rut);
    }

    @Override
    public void deleteClienteEntity(ClienteEntity cliente) {

        this.clienteRepository.deleteAll();
    }


}
