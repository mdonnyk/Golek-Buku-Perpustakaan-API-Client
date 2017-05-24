package control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class Peminjaman extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String p = request.getParameter("p");
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession();
        JsonObject output;
        Pinjam peminjaman = new Pinjam();
        
        Anggota a = (Anggota) session.getAttribute("anggota");
        
        if (p.equalsIgnoreCase("Perpustakaan A")) {
            output = ClientAPI.pinjamBukuA(a.getIdA(), isbn);
        }
        else{
            output = ClientAPI.pinjamBukuB(a.getIdB(), isbn);
        }
        
        String hasil = output.getString("Hasil");
        peminjaman.setTanggal_pinjam(output.getString("tgl_pinjam"));
        peminjaman.setTanggal_kembali(output.getString("tgl_kembali"));

        
        System.out.println("Hasil");
        request.setAttribute("peminjaman", peminjaman);
        RequestDispatcher rq = request.getRequestDispatcher("infoPinjam.jsp");
        rq.forward(request, response);
        
    }


}
