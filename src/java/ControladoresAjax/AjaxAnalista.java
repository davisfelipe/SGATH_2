package ControladoresAjax;

import DB.AccesoUsuarios;
import Estructuras.Sesion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "AjaxAnalista", urlPatterns = {"/AjaxAnalista"})
public class AjaxAnalista extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AjaxAnalista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxAnalista at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out= response.getWriter();
        Sesion sesion=Sesion.getInstance();
        AccesoUsuarios usuarios=new AccesoUsuarios();
        String opcion=request.getParameter("opcion");
        switch(opcion){
            case"1":
                out.print(sesion.obtenerSesion());
                break;
            case "2":                
                usuarios.fechaSalida(sesion.obtenerUsuario());
                sesion.cerrarSesion();
                break;
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
