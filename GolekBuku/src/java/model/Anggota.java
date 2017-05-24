package model;

import service.DatabaseManager;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;






public class Anggota
{
  private String nama;
  private String username;
  private String email;
  private String no_tlp;
  private String alamat;
  private String password;
  private String idA;
  private String idB;

  public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdA() {
        return idA;
    }

    public void setIdA(String idA) {
        this.idA = idA;
    }

    public String getIdB() {
        return idB;
    }

    public void setIdB(String idB) {
        this.idB = idB;
    }
  
  // Method untuk memasukan anggota ke dalam database PerpusClient
  public static String daftar(Anggota a) throws Exception {
    String output = null;
    Connection conn = null;
    PreparedStatement ps = null;
    conn = DatabaseManager.getConnection();
    try {
      ps = conn.prepareCall("INSERT INTO ANGGOTA (nama, username, password, email, telp, alamat, ida, idb) VALUES (?,?,?,?,?,?,?,?)");
      ps.setString(1, a.getNama());
      ps.setString(2, a.getUsername());
      ps.setString(3, a.getPassword());
      ps.setString(4, a.getEmail());
      ps.setString(5, a.getNo_tlp());
      ps.setString(6, a.getAlamat());
      ps.setString(7, a.getIdA());
      ps.setString(8, a.getIdB());
      ps.executeUpdate();
      conn.commit();
      output = "Data sudah ditambahkan";
    }
    catch (SQLException ex) {
      throw ex;
    }
    finally {
      try {
        ps.close();
        conn.close();
      } catch (SQLException ex) {
        throw ex;
      }
    }
    return output;
  }    
}