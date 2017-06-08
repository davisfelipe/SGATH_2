/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresAjax;

import DB.AnalistaProgramacion;
import Estructuras.FasesPrograma;
import Estructuras.RequerimientoEspecifico;
import Estructuras.RequerimientoInicial;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author davis
 */
@WebServlet(name = "AjaxGestionProgramacion", urlPatterns = {"/AjaxGestionProgramacion"})
public class AjaxGestionProgramacion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AjaxGestionProgramacion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxGestionProgramacion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        String opcion =request.getParameter("opcion");
        AnalistaProgramacion analista=new AnalistaProgramacion();
        switch(opcion){
            case"1":
                List requerimientos=analista.requerimientos();
                out.print("<table>");
                out.print("<tr>");
                out.print("<td>");
                out.print("ID");
                out.print("</td>");
                out.print("<td>");
                out.print("Nombre de Requerimiento");
                out.print("</td>");
                out.print("<td>");
                out.print("Cliente");
                out.print("</td>");
                out.print("</tr>");
                for(int a=0;a<requerimientos.size();a++){
                    RequerimientoInicial obtenido=(RequerimientoInicial) requerimientos.get(a);
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(obtenido.id);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(obtenido.nombre);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(obtenido.cliente);
                    out.print("</td>");
                    out.print("</tr>");
                }        
                out.print("</table>");
                break;
            case "2":
                String parametro=request.getParameter("dato");
                int id=Integer.parseInt(parametro);
                List requerimiento=analista.FasesPerfil(id);
                out.print("<h2>Fases</h2>");
                out.print("<table>");
                out.print("<tr>");
                out.print("<td>");
                out.print("ID");
                out.print("</td>");
                out.print("<td>");
                out.print("Descripcion");
                out.print("</tr>");
                out.print("<tr>");
                for(int a=0;a<requerimiento.size();a++){
                    FasesPrograma obtenido=(FasesPrograma) requerimiento.get(a);
                    out.print("<tr>");
                    out.print("<td>");
                    out.print(obtenido.id);
                    out.print("</td>");
                    out.print("<td>");
                    out.print(obtenido.descripcion);
                    out.print("</tr>");
                }
                out.print("</table>");
                break;
            case"3":
                
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
