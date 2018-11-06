/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.session.konversitekanan;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Notebook
 */
public class konversiServlet extends HttpServlet {

    @EJB
    private konversitekanan konversitekanan;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {            
            out.println("<html>");
            out.println("<body");
            out.println("<h1><center>Tugas RSBK Konversi Tekanan</center></h1>");
            String tekanan = request.getParameter("tekanan");
            
            if ((tekanan != null) && (tekanan.length() > 0)) {
                double d = Double.parseDouble(tekanan);
                if (request.getParameter("Atm Ke Bar") != null){
                    String atba = konversitekanan.atm_bar(d);
                    out.println("<p> HASIL KONVERSI TEKANAN: "+atba+"</p>");
                }  
                if (request.getParameter("Atm Ke MPa") != null) {
                    String atmp = konversitekanan.atm_mpa(d);
                    out.println("<p> HASIL KONVERSI TEKANAN: "+atmp+ " .</p>");
                }
                if (request.getParameter("Atm Ke PSI") != null) {
                    String atps = konversitekanan.atm_psi(d);
                    out.println("<p> HASIL KONVERSI TEKANAN: " +atps+ " .</p>");
                }
                if (request.getParameter("Bar Ke Atm") != null) {
                    String baat = konversitekanan.bar_atm(d);
                    out.println("<p> HASIL KONVERSI TEKANAN: "+baat+ " .</p>");
                }
                
            } else {
                out.println("<center>");
                out.println("<p>Masukan Tekanan:</p>");
                out.println("<form method=\"get\">");
                out.println("<p><input type=\"text\" name=\"tekanan\" size=\"20\"></p>");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<input type=\"submit\" name=\"Atm Ke Bar\" value=\"Atm Ke Bar\">");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<input type=\"submit\" name=\"Atm Ke MPa\" value=\"Atm Ke MPa\">");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<input type=\"submit\" name=\"Atm Ke PSI\" value=\"Atm Ke PSI\">");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<input type=\"submit\" name=\"Bar Ke Atm\" value=\"Bar Ke atm\">");
                out.println("</center>");
                out.println("</form>");
            }
        
        } finally {
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            out.close();
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
