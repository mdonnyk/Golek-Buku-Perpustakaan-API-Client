/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Anggota;
import model.Pinjam;
import service.ClientAPI;

/**
 *
 * @author Michael Donny Kusuma
 */
public class Kembali extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String p = request.getParameter("p");
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession();
        String output;
        RequestDispatcher rq = null;

        Anggota agt = (Anggota) session.getAttribute("anggota");
        
        if (p.equalsIgnoreCase("Perpustakaan A")) {
            output = ClientAPI.kembaliBukuA(agt.getIdA(), isbn);
        }
        else{
            output = ClientAPI.kembaliBukuB(agt.getIdB(), isbn);
        }
        request.setAttribute("info", output);
        rq = request.getRequestDispatcher("information.jsp");
        rq.forward(request, response);
    }

    
}
