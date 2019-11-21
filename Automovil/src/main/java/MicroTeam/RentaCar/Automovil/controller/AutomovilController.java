package MicroTeam.RentaCar.Automovil.controller;

import MicroTeam.RentaCar.Automovil.entity.AutomovilEntity;
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


}
