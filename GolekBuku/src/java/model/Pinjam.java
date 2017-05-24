package model;



public class Pinjam {
    private String ID_Peminjam;
    private String ID_Buku;
    private String tanggal_pinjam;
    private String tanggal_kembali;
    private String status;
    private String judul;
    private String perpustakaan;

    public String getID_Peminjam() {
      return ID_Peminjam;
    }

    public void setID_Peminjam(String ID_Peminjam) {
      this.ID_Peminjam = ID_Peminjam;
    }

    public String getID_Buku() {
      return ID_Buku;
    }

    public void setID_Buku(String ID_Buku) {
      this.ID_Buku = ID_Buku;
    }

    public String getTanggal_pinjam() {
      return tanggal_pinjam;
    }

    public void setTanggal_pinjam(String tanggal_pinjam) {
      this.tanggal_pinjam = tanggal_pinjam;
    }

    public String getTanggal_kembali() {
      return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
      this.tanggal_kembali = tanggal_kembali;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPerpustakaan() {
        return perpustakaan;
    }

    public void setPerpustakaan(String perpustakaan) {
        this.perpustakaan = perpustakaan;
    }
}