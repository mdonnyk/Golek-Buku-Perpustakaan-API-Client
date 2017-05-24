package control;

import model.Anggota;
import service.ClientAPI;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Daftar extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        RequestDispatcher rq = null;
        Anggota a = new Anggota();
        try{
            a.setNama(request.getParameter("nama"));
            a.setUsername(request.getParameter("username"));
            a.setEmail(request.getParameter("email"));
            a.setPassword(request.getParameter("password"));
            a.setNo_tlp(request.getParameter("no"));
            a.setAlamat(request.getParameter("alamat"));
            
            // Mengecek kelengkapan dari data inputan
            if (a.getNama().isEmpty() ||
                a.getUsername().isEmpty() ||
                a.getEmail().isEmpty() ||
                a.getPassword().isEmpty() ||
                a.getNo_tlp().isEmpty() ||
                a.getAlamat().isEmpty()) {
                
                throw new Exception("Data pendaftaran tidak lengkap");
                
            }
            
            // Mengecek no telpon
            try{
                Integer.getInteger(a.getNo_tlp());
            }
            catch(Exception e){
                throw new Exception("masukan no telepon salah");
            }
            
            // Mengirim data JSON ke Perpus API server (Need Exception)
            a.setIdA(ClientAPI.daftarAnggotaA(a));
            a.setIdB(ClientAPI.daftarAnggotaB(a));
            // Memasukan anggota ke dalam dalam database lokal
            String result = Anggota.daftar(a);
            
            // Mengirimkan info jika pendaftaran berhasil
            request.setAttribute("info", result);
            rq = request.getRequestDispatcher("information.jsp");
            rq.forward(request, response);
        }
        // Menangkap kode error, menampilkan ke error.jsp
        catch (Exception e){
            request.setAttribute("error", e.getMessage());
            rq = request.getRequestDispatcher("error.jsp");
            rq.forward(request, response);  
        }    
    }
    
}