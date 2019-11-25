package com.automotora.MScolaboradores.controller;
import com.automotora.MScolaboradores.entity.ColaboradorEntity;
import com.automotora.MScolaboradores.service.ColaboradorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(value = {})
@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @GetMapping("/all")
    public ResponseEntity<?> obtenerPersonas() {  // funcionando =)
        ResponseEntity<?> response;
        try {
            List<ColaboradorEntity> personas = this.service.buscarTodos();
            response = new ResponseEntity<>(personas, HttpStatus.OK);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/{rut}")
    public ResponseEntity<?> getPorRut(@PathVariable String rut) {
        ResponseEntity<?> response;
        System.out.println("El rut: "+rut);
        try {
            ColaboradorEntity persona = this.service.buscarPorRut(rut);
            response = new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/ingresar")
    public ResponseEntity<?> agregarEjecutivo(@RequestBody ColaboradorEntity persona) {
        ResponseEntity<?> response;
        try {
            this.service.ingresar(persona);
            response = new ResponseEntity<>(persona.getRut(), HttpStatus.CREATED);
            this.service.asignarSueldoBase(persona.getNivelPermiso(), persona);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
            response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



}
