/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.System.out;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;

/**
 *
 * @author yepes
 */
@WebServlet(urlPatterns = {"/addNewUser"})
public class addNewUser extends HttpServlet {

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
        Enumeration<String> parameterNames = request.getParameterNames();
        boolean existe = false;
        String username = request.getParameter("username");
        String dni = request.getParameter("dni");
        String datos = request.getParameter("username") + ";" + request.getParameter("pass") + ";" + request.getParameter("nombre") + ";" + request.getParameter("apellidos") + ";" + request.getParameter("dni") + ";" + request.getParameter("correo");
        String directorio_de_ejecucion_de_la_aplicacion = new File(".").getCanonicalPath();
        String sFile = directorio_de_ejecucion_de_la_aplicacion + "\\" + "registro.txt";
        File fichero = new File(sFile);
        System.out.println(sFile);
        try (BufferedReader br = new BufferedReader(new FileReader(sFile))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(";");
                if (palabras.length > 0 && palabras[0].equals(username) || palabras[4].equals(dni)){
                    existe = true;
                }
            }
        }
        if(!existe){
           try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true))) {
                writer.write(datos);
                writer.newLine();
            } 
           Cookie lugarCookie = new Cookie("lugar", request.getParameter("lugar"));
           response.addCookie(lugarCookie);
           response.sendRedirect("index.html");
        }else{
           request.setAttribute("error", "El usuario ya existe. Por favor, elige otro nombre de usuario.");
           RequestDispatcher dispatcher = request.getRequestDispatcher("RegisterServlet");
           dispatcher.forward(request, response);
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
