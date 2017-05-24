package model;

public class Buku {
  private String ISBN;
  private String Judul;
  private String Penulis;
  private String Tahun_Terbit;
  private String Penerbit;
  private int Ketersediaan;
  private String perpustakaan;
  
  public String getISBN()
  {
    return ISBN;
  }
  
  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }
  
  public String getJudul() {
    return Judul;
  }
  
  public void setJudul(String Judul) {
    this.Judul = Judul;
  }
  
  public String getPenulis() {
    return Penulis;
  }
  
  public void setPenulis(String Penulis) {
    this.Penulis = Penulis;
  }
  
  public String getTahun_Terbit() {
    return Tahun_Terbit;
  }
  
  public void setTahun_Terbit(String Tahun_Terbit) {
    this.Tahun_Terbit = Tahun_Terbit;
  }
  
  public String getPenerbit() {
    return Penerbit;
  }
  
  public void setPenerbit(String Penerbit) {
    this.Penerbit = Penerbit;
  }
  
  public int getKetersediaan() {
    return Ketersediaan;
  }
  
  public void setKetersediaan(int Ketersediaan) {
    this.Ketersediaan = Ketersediaan;
  }

    public String getPerpustakaan() {
        return perpustakaan;
    }

    public void setPerpustakaan(String perpustakaan) {
        this.perpustakaan = perpustakaan;
    }
}