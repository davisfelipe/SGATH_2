package Estructuras;
public class Sesion {
    public static Sesion instancia=null;
    private boolean sesion;
    private String nombre;
    private String apellido;
    private String usuario;
    public static Sesion getInstance(){
        if(Sesion.instancia==null){
            Sesion.instancia=new Sesion();
        }
        return Sesion.instancia;
    }
    public void iniciarSesion(String nombre,String apellido,String usuario){
        this.nombre=nombre;
        this.apellido=apellido;
        this.usuario=usuario;
        this.sesion=true;
    }
    public void cerrarSesion(){
        this.nombre=null;
        this.apellido=null;
        this.usuario=null;
        this.sesion=false;
    }
    public String obtenerSesion(){
        return this.nombre+" "+this.apellido;
    }
    public String obtenerUsuario(){
        return this.usuario;
    }
}
