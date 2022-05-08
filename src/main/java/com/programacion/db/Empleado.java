package com.programacion.db;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Empleado {

    //atributos de la tabla empleado en la base de datos
    @Setter @Getter private Integer cedula;
    @Setter @Getter private String nombre;
    @Setter @Getter private String apellido;
    @Setter @Getter private String correo;
    @Setter @Getter private Date fechana;
    @Setter @Getter private String direccion;
    @Setter @Getter private Integer telefono;
    @Setter @Getter private String vacunado;

}
