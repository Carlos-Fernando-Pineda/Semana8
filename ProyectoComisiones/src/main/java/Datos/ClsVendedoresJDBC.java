/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ClsVendedor;
import java.sql.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public class ClsVendedoresJDBC {
    private static final String SQL_SELECT = "select * from tb_vendedores";
    private static final String SQL_INSERT = "insert into tb_vendedores (nombre,enero,febrero,marzo) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update tb_vendedores set nombre =?,enero=?,febrero=?,marzo=? where codigo=?";
    private static final String SQL_DELETE = "delete from tb_vendedores where codigo=?";
    
    //seleccionar informacion
    public List<ClsVendedor> seleccion(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsVendedor vendedor = null;
        List<ClsVendedor> vendedores = new ArrayList<ClsVendedor>();
        
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                Double enero = rs.getDouble("nota1");
                Double febrero = rs.getDouble("nota2");
                Double marzo = rs.getDouble("marzo");
                vendedor = new ClsVendedor();
                vendedor.setCodigo(codigo);
                vendedor.setNombre(nombre);
                vendedor.setEnero(enero);
                vendedor.setFebrero(febrero);
                vendedor.setMarzo(marzo);
                vendedores.add(vendedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        return vendedores;
    }
    
    public int insert(ClsVendedor vendedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, vendedor.getNombre());
            stmt.setDouble(2, vendedor.getEnero());
            stmt.setDouble(3, vendedor.getFebrero());
            stmt.setDouble(4, vendedor.getMarzo());
         
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
    public int actualizar(ClsVendedor vendedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, vendedor.getNombre());
            stmt.setDouble(2, vendedor.getEnero());
            stmt.setDouble(3, vendedor.getFebrero());
            stmt.setDouble(4, vendedor.getMarzo());
            stmt.setInt(5, vendedor.getCodigo());
         
            
            System.out.println("ejecutando query:" + SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
    public int borrar(ClsVendedor vendedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, vendedor.getCodigo());
         
            
            System.out.println("ejecutando query:" + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
}
