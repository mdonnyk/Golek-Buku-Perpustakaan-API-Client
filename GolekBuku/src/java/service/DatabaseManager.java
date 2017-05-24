package service;

import model.Anggota;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;






public class DatabaseManager {
  
    public static Connection getConnection() {
        String host = "localhost";
        String port = "1521";
        String db = "xe";
        String usr = "admin";
        String pwd = "admin";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Maaf driver class tidak ditemukan");
            System.out.println(ex.getMessage()); }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + db, usr, pwd);
        }
        catch (SQLException ex) {
            System.out.println("Maaf koneksi tidak berhasil");
            System.out.println(ex.getMessage());
        }

        if (conn != null) {
            System.out.println("Koneksi ke database terbentuk");
        }
        else {
            System.out.println("Koneksi gagal terbentuk");
        }
        return conn;
    }
  
  
 public static Anggota login(String username, String password) {
    Anggota a = new Anggota();
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    conn = getConnection();
    try {
        st = conn.createStatement();
        rs = st.executeQuery("SELECT * FROM anggota WHERE username='" + username + "' and password='" + password + "'");
        while (rs.next()) {
            a.setNama(rs.getString(1));
            a.setUsername(rs.getString(2));
            a.setPassword(rs.getString(3));
            a.setEmail(rs.getString(4));
            a.setNo_tlp(rs.getString(5));
            a.setAlamat(rs.getString(6));
            a.setIdA(rs.getString(7));
            a.setIdB(rs.getString(8));
      }
      try
      {
        rs.close();
        st.close();
        conn.close();
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
      if (a.getUsername() != null) {}
    }
    catch (SQLException ex)
    {
      System.out.println(ex.getMessage());
    } finally {
      try {
        rs.close();
        st.close();
        conn.close();
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }
    return a;
  }

    
}