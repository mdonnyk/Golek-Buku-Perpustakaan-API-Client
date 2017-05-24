package control;

import model.Anggota;
import service.DatabaseManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="Login", urlPatterns={"/Login"})
public class Login extends HttpServlet {
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    RequestDispatcher rq = null;
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    try{
        Anggota a = DatabaseManager.login(username, password);
        if (a.getUsername() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("anggota", a);
            rq = request.getRequestDispatcher("dashboard.jsp");
        }
        else {
            throw new Exception("Gagal Login");
        }
    }
    catch(Exception e){
        request.setAttribute("error", e.getMessage());
        rq = request.getRequestDispatcher("error.jsp");
    }
    
    rq.forward(request, response);
    
    
  }
}