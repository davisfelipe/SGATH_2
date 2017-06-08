package DB;

import Estructuras.Sesion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author davis
 */
public class AccesoUsuarios {
    private Oracle database;
    private PreparedStatement preparar;
    private ResultSet resultado;

    public AccesoUsuarios() {
        this.database = new Oracle();
    }
    public boolean buscarUsuario(String usuario, String contraseña){
        boolean respuesta=false;
        try {
            preparar=database.abrirConexion().prepareStatement("select contrasena from empleado where usuario=?");
            preparar.setString(1, usuario);
            resultado=preparar.executeQuery();
            resultado.next();
            if(resultado.getString(1).equals(contraseña)){
                respuesta=true;
            }else{
                respuesta=false;
            }
            preparar.close();
        } catch (SQLException ex) {
        }
        return respuesta;
    }
    public String buscarIdentificacion(String usuario){
        String respuesta=null;
        try {
            preparar=database.abrirConexion().prepareStatement("select nombreempleado||' '||apellidoempleado from empleado where usuario=?");
            preparar.setString(1, usuario);
            resultado=preparar.executeQuery();
            resultado.next();
            respuesta=resultado.getString(1); 
            preparar.close();
        } catch (SQLException ex) {
        }
        
        return respuesta;
    }
    public void asignarIdentificacion(String user) {
        Sesion sesion = Sesion.getInstance();
        try {
            preparar=database.abrirConexion().prepareStatement("select nombreempleado, apellidoempleado from empleado where usuario=?");
            preparar.setString(1, user);
            resultado=preparar.executeQuery();
            resultado.next();
            sesion.iniciarSesion(resultado.getString(1), resultado.getString(2),user);
            preparar.close();
//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
        }
    }
    public String obtenerCargo(String user){
        String respuesta=null;
        try {
            preparar=database.abrirConexion().prepareStatement("select cargoempleado from empleado where usuario=?");
            preparar.setString(1, user);
            resultado=preparar.executeQuery();
            resultado.next();
            respuesta=resultado.getString(1);
            preparar.close();
        } catch (SQLException ex) {
        }
        
        return respuesta;
    }
    public void fechaIngreso(String user){
        try {
            preparar=database.abrirConexion().prepareStatement("update empleado set fechaentrada=sysdate where usuario=?");
            preparar.setString(1, user);
            preparar.executeUpdate();
            preparar.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"se puteo");
        }
    }
    public void fechaSalida(String user){
        try {
            preparar=database.abrirConexion().prepareStatement("update empleado set fechasalida=sysdate where usuario=?");
            preparar.setString(1, user);
            preparar.executeUpdate();
            preparar.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"se puteo");
        }
    }
}
