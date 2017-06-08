/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author davis
 */
@WebServlet(name = "AjaxAcceso", urlPatterns = {"/AjaxAcceso"})
public class AjaxAcceso extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AjaxAcceso</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxAcceso at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        AccesoUsuarios usuario=new AccesoUsuarios();
        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        Sesion sesion=Sesion.getInstance();
        String opcion=request.getParameter("opcion");
        String user=request.getParameter("user");
        String pass=request.getParameter("pass");
        switch(opcion){
            case "1":
                if(usuario.buscarUsuario(user, pass)){
                    out.print("true");
                }else{
                    out.print("false");
                }
                break;
            case "2":
                usuario.buscarIdentificacion(user);
                usuario.asignarIdentificacion(user);
                break;
            case "3":
                out.print(usuario.obtenerCargo(user));
                break;
            case "4":
                usuario.fechaIngreso(user);
                break;
        }
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
