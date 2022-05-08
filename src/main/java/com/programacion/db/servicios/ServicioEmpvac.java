package com.programacion.db.servicios;

import com.programacion.db.Empleado;
import com.programacion.db.Empvac;

import java.util.List;

public interface ServicioEmpvac {

    List<Empvac> findById(Integer id);
    List<Empvac> findAll();
    void create(Empvac p);
    void delete (Integer id);

}
