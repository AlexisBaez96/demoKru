package com.programacion.db.servicios;

import com.programacion.db.Empleado;

import java.util.List;

public interface ServicioEmpleado {
    Empleado findById(Integer id);
    List<Empleado> findAll();
    void create(Empleado p);
    void delete (Integer id);
    void update (Integer id, Empleado p);
}
