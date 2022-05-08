package com.programacion.db.servicios;

import com.programacion.db.Empleado;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioEmpleadoImpl implements ServicioEmpleado{

    @Inject
    private DataSource dataSource;

    public Empleado findById  (Integer id){
        Connection con = null;
        Empleado p = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from EMPLEADO where CEDULA = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                p = new  Empleado();
                p.setCedula(id);
                p.setNombre(rs.getString("NOMBRE"));
                p.setApellido(rs.getString("APELLIDO"));
                p.setCorreo(rs.getString("CORREO"));
                p.setFechana(rs.getDate("FECHANA"));
                p.setDireccion(rs.getString("DIRECCION"));
                p.setTelefono(rs.getInt("TELEFONO"));
                p.setVacunado(rs.getString("VACUNADO"));
            }
            rs.close();
            pstmt.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return p;

    }
    public List<Empleado> findAll(){
        List<Empleado> list = new ArrayList<>();
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from EMPLEADO");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Empleado p = new  Empleado();
                p.setCedula(rs.getInt("CEDULA"));
                p.setNombre(rs.getString("NOMBRE"));
                p.setApellido(rs.getString("APELLIDO"));
                p.setCorreo(rs.getString("CORREO"));
                p.setFechana(rs.getDate("FECHANA"));
                p.setDireccion(rs.getString("DIRECCION"));
                p.setTelefono(rs.getInt("TELEFONO"));
                p.setVacunado(rs.getString("VACUNADO"));
                list.add(p);
            }
            rs.close();
            pstmt.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return list;

    }
    public void delete (Integer id){
        Connection con = null;
        try {
            con = dataSource.getConnection();
            String query = "delete from EMPLEADO where CEDULA = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void create(Empleado obj){
        Connection con = null;
        try {
            con = dataSource.getConnection();
            String query = "INSERT INTO EMPLEADO (CEDULA, NOMBRE, APELLIDO, CORREO) values (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, obj.getCedula());
            pstmt.setString(2, obj.getNombre());
            pstmt.setString(3, obj.getApellido());
            pstmt.setString(4, obj.getCorreo());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void update (Integer id, Empleado p){
        Connection con = null;
        try {
            con = dataSource.getConnection();
            String query = "update EMPLEADO set FECHANA = ?, DIRECCION = ?, TELEFONO = ?, VACUNADO = ? where CEDULA = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1,p.getFechana());
            ps.setString(2,p.getDireccion());
            ps.setInt(3,p.getTelefono());
            ps.setString(4,p.getVacunado());
            ps.setInt(5,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
