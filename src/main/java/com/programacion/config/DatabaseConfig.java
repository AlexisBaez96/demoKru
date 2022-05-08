package com.programacion.config;

import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class DatabaseConfig {

    //pool de conexion
    @Produces
    @ApplicationScoped
    public DataSource dataSource(){

        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/krugerBD");
        ds.setUsername("postgres");
        ds.setPassword("admin");

        return ds;
    }
}
