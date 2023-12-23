
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ProveedorDao {
    
    Connection  con ;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor (ruc, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try{
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setInt(3,pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.execute();
            return true;
           
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarProveedor() {
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getInt("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                Listapr.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());

        }
        return Listapr;
    }
    
    public boolean EliminarProveedor(int id){
        String sql ="DELETE FROM proveedor WHERE id = ? " ; // eliminar un registro de la tabla
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id); // El valor proporcionado como parámetro "id" se asigna al primer marcador de posición
            ps.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
             
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){   //para modificar un registro en una tabla
        String sql="UPDATE proveedor SET ruc=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?"; 
      
        try{
            con =cn.getConnection();
            ps =con.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());;
            ps.setString(2, pr.getNombre());
            ps.setInt(3,pr.getTelefono());
            ps.setString(4, pr.getDireccion());;
            ps.setString(5, pr.getRazon());
            ps.setInt(6, pr.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
    //setInt asignar un valor de tipo entero a un marcador de posición en la consulta SQL
    //pr.getRuc() obtiene el valor del atributo "ruc" del objeto Proveedor (pr) y lo asigna al primer marcador de posición en la consulta SQL.
}
