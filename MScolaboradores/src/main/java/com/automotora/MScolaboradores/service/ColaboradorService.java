package com.automotora.MScolaboradores.service;

import com.automotora.MScolaboradores.entity.ColaboradorEntity;

import java.util.List;

public interface ColaboradorService {

    List<ColaboradorEntity> buscarTodos();
    ColaboradorEntity buscarPorRut(String rut);
    String ingresar(ColaboradorEntity persona);
    void asignarSueldoBase(String nivelPermiso, ColaboradorEntity persona);

    /*
    void eliminarEjecutivo(String id);
    void ingresarColaborador(String fecha);
    int calcularBono();
    P buscarColaboradorPorRut(String rut);
    void cambiarNivelPermiso(String permiso);
    */

}
