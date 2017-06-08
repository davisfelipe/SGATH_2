package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Oracle {
    private Connection conexion;
    public Connection abrirConexion(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.conexion=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ADMIN", "Aufseherin88");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "conexion exitosa");
        }
        return this.conexion;
    }
    public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cerrar la base de datos");
        }
    }
}
