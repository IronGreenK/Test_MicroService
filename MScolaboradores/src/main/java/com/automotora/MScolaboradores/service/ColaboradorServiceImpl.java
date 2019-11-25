package com.automotora.MScolaboradores.service;
import com.automotora.MScolaboradores.entity.ColaboradorEntity;
import com.automotora.MScolaboradores.repository.ColaboradorRepository;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {
    //private final ColaboradorEntity colaborador;


    @Autowired
    private ColaboradorRepository repository;

    @Override
    public List<ColaboradorEntity> buscarTodos() {
        return this.repository.findAll();
    }

    @Override
    public ColaboradorEntity buscarPorRut(String rut) {
        return this.repository.findOneByRut(rut).get();
    }

    @Override
    public String ingresar(ColaboradorEntity persona) {

        asignarSueldoBase (persona.getNivelPermiso(), persona);     // en base al nivel permiso
        calcularSueldoMasBono(persona.getFechaIncorporacion(), persona); // en base a la antiguedad
        validadEdad (persona.getSexo(), persona.getFechaNacimiento(), persona); // segun sexo y rango etario

        return "Colaborador ingresado";
    }

    @Override
    public void asignarSueldoBase (String nivelPermiso, ColaboradorEntity persona){
        System.out.println("asignando sueldo");

        if (nivelPermiso.equals("S")){
            persona.setSueldoBase(1500000);}
        else if (nivelPermiso.equals("A")){
            persona.setSueldoBase(1000000);}
        else if (nivelPermiso.equals("V")){
            persona.setSueldoBase(750000);
            System.out.println("asignando sueldo V");}
        else{
        System.out.println("Nivel de permiso debe ser S, A o V");
        }
    }

    void calcularSueldoMasBono (LocalDate incorporacion, ColaboradorEntity persona) {

        LocalDate now = LocalDate.now();
        int antiguedad = Period.between(incorporacion, now).getYears();  // Calcula la edad
            System.out.println("La antiguedad del colaborador es " +antiguedad + " años");
        int sueldoBase = persona.getSueldoBase();
        float bonoPorcentaje = (antiguedad * 0.02f);
        persona.setBonoServicio(bonoPorcentaje*100);
            System.out.println("El porcentaje de bono corresponde a " + bonoPorcentaje * 100);
        float montoBono = persona.getSueldoBase() * bonoPorcentaje;
            System.out.println("el bono de antiguedad equivale a " +montoBono);
        persona.setSueldoMasBono(sueldoBase + montoBono);
            System.out.println("Suedo total es " + persona.getSueldoMasBono());
    }


    public String validadEdad (String sexo, LocalDate nacimiento, ColaboradorEntity persona){

        LocalDate now = LocalDate.now();
        int edad = Period.between(nacimiento, now).getYears();  // Calcula la edad
        System.out.println("La edad del postulante es " +edad);

        if (sexo.equals("F")) {
            if (edad >= 18 && edad < 60) {
                this.repository.save(persona);
                System.out.println("Mujer agregada");
                return "Clienta ingresada";
            } else
                System.out.println("Colaboradora debe ser mayor de 18 y menor de 65 años");
                return "Colaboradora debe ser mayor de 18 y menor de 60 años";
        }
        else if (sexo.equals("M")){
            if (edad >= 18 && edad < 65) {
                this.repository.save(persona);
                System.out.println("Hombre agregada");
                return "Cliente ingresado";
            } else
                System.out.println("Colaborador debe ser mayor de 18 y menor de 65 años");
                return "Colaborador debe ser mayor de 18 y menor de 65 años";
        }
        System.out.println("SOUT sexo de colaborador debe ser indicado como M o F");
        return "sexo de colaborador debe ser indicado como M o F";
    }
}
