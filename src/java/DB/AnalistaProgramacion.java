package DB;

import Estructuras.FasesPrograma;
import Estructuras.RequerimientoEspecifico;
import Estructuras.RequerimientoInicial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalistaProgramacion {
    private Oracle database;
    private PreparedStatement preparar;
    private ResultSet resultado;
    public AnalistaProgramacion(){
        this.database=new Oracle();
    }
    public List requerimientos(){
        List requerimientos=new ArrayList();
        try {
            preparar=database.abrirConexion().prepareStatement("select r.idrequerimiento, r.nombrerequerimiento ,c.nombrecliente from requerimiento r, factura f, cliente c where r.idfacturarequ=f.idfactura and f.idclientefact=c.idcliente and (r.idrequerimiento) in (select idrequerimientoprog from programa)");
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
    public List FasesPerfil(int id){
        List fases=new ArrayList();
        try {
            preparar=database.abrirConexion().prepareStatement("select f.idfase, f.descripcion from fase f , perfil p where f.idperfilfase=p.idperfil and (p.idperfil) in (select idperfilprog from programa where idrequerimientoprog=?)");
            preparar.setInt(1, id);
            resultado=preparar.executeQuery();
            while(resultado.next()){
                FasesPrograma obtenido=new FasesPrograma();
                obtenido.id=resultado.getInt(1);
                obtenido.descripcion=resultado.getString(2);
                fases.add(obtenido);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AnalistaPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fases;
    }
}
