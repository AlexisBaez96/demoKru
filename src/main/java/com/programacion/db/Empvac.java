package com.programacion.db;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class Empvac {

    //atributos de la tabla empvac en la base de datos
    @Setter @Getter private Integer empvac;
    @Setter @Getter private Integer dosis;
    @Setter @Getter private Integer cedula;
    @Setter @Getter private Integer vacuna;
    @Setter @Getter private Date fecha;
}
