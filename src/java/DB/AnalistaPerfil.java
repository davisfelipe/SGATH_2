package DB;

import DB.*;
import Estructuras.Perfiles;
import Estructuras.RequerimientoEspecifico;
import Estructuras.RequerimientoInicial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
            preparar=database.abrirConexion().prepareStatement("select r.idrequerimiento, r.nombrerequerimiento ,c.nombrecliente from requerimiento r, factura f, cliente c where r.idfacturarequ=f.idfactura and f.idclientefact=c.idcliente and (r.idrequerimiento) in (select idrequerimiento from requerimiento MINUS select idrequerimientoprog from programa)");
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
    public RequerimientoEspecifico requerimientoEspecifico(int id){
        RequerimientoEspecifico requerimiento=new RequerimientoEspecifico();
        try {
            preparar=database.abrirConexion().prepareStatement("select idrequerimiento, nombrerequerimiento, salariominimo,salariomaximo,anosexperienciamin,nivelformacion,titulo,genero from requerimiento where idrequerimiento=?");
            preparar.setInt(1, id);
            resultado=preparar.executeQuery();
            resultado.next();
            requerimiento.id=resultado.getInt(1);
            requerimiento.nombre=resultado.getString(2);
            requerimiento.salariomin=resultado.getInt(3);
            requerimiento.salariomax=resultado.getInt(4);
            requerimiento.experiencia=resultado.getInt(5);
            requerimiento.formacion=resultado.getString(6);
            requerimiento.titulo=resultado.getString(7);
            requerimiento.genero=resultado.getString(8);           
        } catch (SQLException ex) {
            Logger.getLogger(AnalistaPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requerimiento;
    }
    public List perfiles(){
        List perfiles=new ArrayList();
        try {
            preparar=database.abrirConexion().prepareStatement("select idperfil,nombreperfil,titulo,experiencia,sueldo from perfil");
            resultado=preparar.executeQuery();
            while(resultado.next()){
                Perfiles obtenido=new Perfiles();
                obtenido.id=resultado.getInt(1);
                obtenido.nombre=resultado.getString(2);
                obtenido.titulo=resultado.getString(3);
                obtenido.experiencia=resultado.getInt(4);
                obtenido.sueldo=resultado.getInt(5);
                perfiles.add(obtenido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnalistaPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfiles;
    }
    public void asignarPerfil(String idrequerimiento,String idperfil) throws SQLException{
        preparar=database.abrirConexion().prepareStatement("insert into programa values (?,?,?,?,?,?)");
        preparar.setInt(1, Integer.parseInt(idrequerimiento+idperfil));
        preparar.setInt(2, Integer.parseInt(idperfil));
        preparar.setInt(3, Integer.parseInt(idrequerimiento));
        preparar.setNull(4,Types.INTEGER);
        preparar.setNull(5,Types.INTEGER);
        preparar.setNull(6,Types.INTEGER);
        preparar.executeUpdate();
    }
}
