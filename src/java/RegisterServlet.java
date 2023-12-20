/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yepes
 */
@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        String errorMessage = (String) request.getAttribute("error");
        try (PrintWriter out = response.getWriter()) {    
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"styles.css\"/>");
            out.println("<script src=\"scripts.js\"></script>");
            out.println("<title>Registro Nike Jobs</title>");            
            out.println("</head>");
            
            out.println("<body>");
            out.println("<div class=\"content\">");
            out.println("<img src=\"./images/nike-logo-white.png\" alt=\"alt\" id=\"nike-logo\"/>");
            out.println("<div class=\"box\">");
            out.println("<form id=\"complete-register\" action=\"addNewUser\" method=\"post\">");

            out.println("<div class=\"input-div\">");
            out.println("<h1>Registro Nike Jobs</h1>");
            out.println("<p>Nombre</p>");
            out.println("<input id=\"nombre\" type=\"text\" name=\"nombre\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>Apellidos</p>");
            out.println("<input id=\"apellidos\" type=\"text\" name=\"apellidos\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>Dirección de correo</p>");
            out.println("<input id=\"correo\" type=\"text\" name=\"correo\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>DNI</p>");
            out.println("<input id=\"dni\" type=\"text\" name=\"dni\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>Usuario</p>");
            out.println("<input id=\"user\" type=\"text\" name=\"username\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>Lugar donde quieres trabajar</p>");
            out.println("<input id=\"lugar\" type=\"text\" name=\"lugar\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>Contraseña</p>");
            out.println("<input id=\"password\" type=\"password\" name=\"pass\" class=\"box-button\"/>");
            out.println("</div>");
            
            out.println("<div class=\"input-div\">");
            out.println("<p>Repetir Contraseña</p>");
            out.println("<input id=\"passwordRepeat\" type=\"password\" value=\"\" class=\"box-button\"/>");
            out.println("</div>");
            out.println("</form>");
            if (errorMessage != null) {
                out.println("<p style=\"color: red; width: 85%;\">" + errorMessage + "</p>");
            }
            out.println("<input class=\"submit-button\" type=\"submit\" onclick=\"register()\"/>");
            out.println("</div>");
            out.println("</div>");
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
        processRequest(request, response);
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
