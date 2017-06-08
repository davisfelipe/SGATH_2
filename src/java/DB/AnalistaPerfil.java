package DB;

import DB.*;
import Estructuras.RequerimientoInicial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AnalistaPerfil {
    private Oracle database;
    private PreparedStatement preparar;
    private ResultSet resultado;
    public AnalistaPerfil(){
        this.database=new Oracle();
    }
    public List requerimientos(){
        List requerimientos=new ArrayList();
        try {
            preparar=database.abrirConexion().prepareStatement("select r.idrequerimiento, r.nombrerequerimiento ,c.nombrecliente from requerimiento r, factura f, cliente c where r.idfacturarequ=f.idfactura and f.idclientefact=c.idcliente");
            resultado=preparar.executeQuery();
            while(resultado.next()){
                RequerimientoInicial obtenido=new RequerimientoInicial();
                obtenido.id=resultado.getInt(1);
                obtenido.nombre=resultado.getString(2);
                obtenido.cliente=resultado.getString(3);
                requerimientos.add(obtenido);
            }
        } catch (SQLException ex) {
        }
        return requerimientos;
    }
    public List requerimientoEspecifico(int id){
        List requerimiento=new ArrayList();
        try {
            preparar=database.abrirConexion().prepareStatement("select idrequerimiento, idfacturarequ, nombrerequerimiento, salariominimo,salariomaximo,anosexperienciamin,nivelformacion,titulo,genero from requerimiento where idrequerimiento=?");
            preparar.setInt(1, id);
            resultado=preparar.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(AnalistaPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requerimiento;
    }
}
