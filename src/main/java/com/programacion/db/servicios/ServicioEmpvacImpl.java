package com.programacion.db.servicios;

import com.programacion.db.Empvac;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioEmpvacImpl implements ServicioEmpvac{

    @Inject
    private DataSource dataSource;

    public List<Empvac> findById  (Integer id){
        List<Empvac> list = new ArrayList<>();
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from EMPVAC where CEDULA = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Empvac p = new  Empvac();
                p.setFecha(rs.getDate("FECHA"));
                p.setDosis(rs.getInt("DOSIS"));
                p.setEmpvac(rs.getInt("EMPVAC"));
                p.setCedula(id);
                p.setVacuna(rs.getInt("VACUNA"));
                list.add(p);
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
        return list;

    }
    public List<Empvac> findAll(){
        List<Empvac> list = new ArrayList<>();
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from EMPVAC");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Empvac p = new  Empvac();
                p.setFecha(rs.getDate("FECHA"));
                p.setDosis(rs.getInt("DOSIS"));
                p.setEmpvac(rs.getInt("EMPVAC"));
                p.setCedula(rs.getInt("CEDULA"));
                p.setVacuna(rs.getInt("VACUNA"));
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
            String query = "delete from EMPVAC where EMPVAC = ?";
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

    public void create(Empvac obj){
        Connection con = null;
        try {
            con = dataSource.getConnection();
            String query = "INSERT INTO EMPVAC (FECHA, DOSIS, CEDULA, VACUNA) values (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setDate(1, obj.getFecha());
            pstmt.setInt(2, obj.getDosis());
            pstmt.setInt(3, obj.getCedula());
            pstmt.setInt(4, obj.getVacuna());
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

//    public void update (Integer id, Empleado p){
//        Connection con = null;
//        try {
//            con = dataSource.getConnection();
//            String query = "update EMPLEADO set FECHANA = ?, DIRECCION = ?, TELEFONO = ?, VACUNADO = ? where CEDULA = ?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setDate(1,p.getFechana());
//            ps.setString(2,p.getDireccion());
//            ps.setInt(3,p.getTelefono());
//            ps.setString(4,p.getVacunado());
//            ps.setInt(5,id);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
