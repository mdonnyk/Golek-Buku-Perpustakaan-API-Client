/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import model.Anggota;
import model.Buku;
import model.Pinjam;

/**
 *
 * @author Michael Donny Kusuma
 */
public class ClientAPI {
    static String urlPerpusA = "http://172.23.26.53:9090/PerpustakaanA/api/";
    static String urlPerpusB = "http://172.23.26.53:9090/PerpustakaanB/api/";
    static String namaPerpusA = "Perpustakaan A";
    static String namaPerpusB = "Perpustakaan B";
     
    public static String daftarAnggotaA(Anggota agg) throws Exception {
        String result = null;
        URL url = new URL(urlPerpusA + "DaftarAnggota");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        JsonObjectBuilder anggota = Json.createObjectBuilder();
        anggota.add("nama", agg.getNama());
        anggota.add("email", agg.getEmail());
        anggota.add("password", agg.getPassword());
        anggota.add("no", agg.getNo_tlp());
        anggota.add("alamat", agg.getAlamat());
        JsonObject anggotaJsonObject = anggota.build();
        String input = anggotaJsonObject.toString();

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();


        if (conn.getResponseCode() != 201) {
           throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            result = output;
        }

        conn.disconnect();

        return result;
    }

    public static String daftarAnggotaB(Anggota agg) throws Exception {
        String result = null;
        URL url = new URL(urlPerpusB + "DaftarAnggota");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        JsonObjectBuilder anggota = Json.createObjectBuilder();
        anggota.add("nama", agg.getNama());
        anggota.add("email", agg.getEmail());
        anggota.add("password", agg.getPassword());
        anggota.add("no", agg.getNo_tlp());
        anggota.add("alamat", agg.getAlamat());
        JsonObject anggotaJsonObject = anggota.build();
        String input = anggotaJsonObject.toString();

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();


        if (conn.getResponseCode() != 201) {
           throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            result = output;
        }

        conn.disconnect();

        return result;
    }

    public static Buku[] cariBuku(String query) {
      Buku[] buku = null;
      JsonObject[] bukuJsonA = null;
      JsonObject[] bukuJsonB = null;
      URL url;
      HttpURLConnection conn;
      InputStream is;
      JsonReader jsonReader;
      JsonObject jsonObject;
      JsonArray bukuArray;
      int index;

      try {
          //url = new URL(a.getUrl() + "Cari?query=" + query);
          url = new URL(urlPerpusA + "Cari?query=" + query);
          
          conn = (HttpURLConnection)url.openConnection();
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Accept", "application/json");

          if (conn.getResponseCode() != 200)
          {
            throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
          }

          is = conn.getInputStream();


          jsonReader = Json.createReader(is);
          jsonObject = jsonReader.readObject();


          jsonReader.close();
          is.close();
          conn.disconnect();

          bukuArray = jsonObject.getJsonArray("buku");
          bukuJsonA = new JsonObject[bukuArray.size()];
          index = 0;
          for (JsonValue value : bukuArray) {
            bukuJsonA[(index++)] = ((JsonObject)value);
          } 

          //////

          //url = new URL(b.getUrl() + "Cari?query=" + query);
          url = new URL(urlPerpusB + "Cari?query=" + query);
          conn = (HttpURLConnection)url.openConnection();
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Accept", "application/json");

          if (conn.getResponseCode() != 200)
          {
            throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
          }

          is = conn.getInputStream();


          jsonReader = Json.createReader(is);
          jsonObject = jsonReader.readObject();


          jsonReader.close();
          is.close();
          conn.disconnect();

          bukuArray = jsonObject.getJsonArray("buku");
          bukuJsonB = new JsonObject[bukuArray.size()];
          index = 0;
          for (JsonValue value : bukuArray) {
            bukuJsonB[(index++)] = ((JsonObject)value);
          } 
      }



      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      buku = new Buku[bukuJsonA.length+bukuJsonB.length];
      for (int i = 0; i < bukuJsonA.length; i++) {
          buku[i] = new Buku();
          buku[i].setISBN(bukuJsonA[i].getString("isbn"));
          buku[i].setJudul(bukuJsonA[i].getString("judul"));
          buku[i].setKetersediaan(bukuJsonA[i].getInt("ketersediaan"));
          buku[i].setPenerbit(bukuJsonA[i].getString("penerbit"));
          buku[i].setPenulis(bukuJsonA[i].getString("penulis"));
          buku[i].setTahun_Terbit(bukuJsonA[i].getString("tahun"));
          buku[i].setPerpustakaan("Perpustakaan A");
      }

      for (int i = bukuJsonA.length; i < buku.length; i++) {
          buku[i] = new Buku();
          buku[i].setISBN(bukuJsonB[i-bukuJsonA.length].getString("isbn"));
          buku[i].setJudul(bukuJsonB[i-bukuJsonA.length].getString("judul"));
          buku[i].setKetersediaan(bukuJsonB[i-bukuJsonA.length].getInt("ketersediaan"));
          buku[i].setPenerbit(bukuJsonB[i-bukuJsonA.length].getString("penerbit"));
          buku[i].setPenulis(bukuJsonB[i-bukuJsonA.length].getString("penulis"));
          buku[i].setTahun_Terbit(bukuJsonB[i-bukuJsonA.length].getString("tahun"));
          buku[i].setPerpustakaan("Perpustakaan B");
      }

      return buku;
    }

    public static JsonObject pinjamBukuA(String id, String isbn)
    {
      JsonObject hasil = null;
      try {
        URL url = new URL(urlPerpusA + "PinjamBuku?id=" + id + "&isbn=" + isbn);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200)
        {
          throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
        }

        InputStream is = conn.getInputStream();


        JsonReader jsonReader = Json.createReader(is);


        JsonObject jsonObject = jsonReader.readObject();


        hasil = jsonObject;
        



        jsonReader.close();
        is.close();
        conn.disconnect();
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      return hasil;
    }
    
    public static JsonObject pinjamBukuB(String id, String isbn)
    {
      JsonObject hasil = null;
      try {
        URL url = new URL(urlPerpusB + "PinjamBuku?id=" + id + "&isbn=" + isbn);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200)
        {
          throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
        }

        InputStream is = conn.getInputStream();


        JsonReader jsonReader = Json.createReader(is);


        JsonObject jsonObject = jsonReader.readObject();


        hasil = jsonObject;



        jsonReader.close();
        is.close();
        conn.disconnect();
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      return hasil;
    }

    public static String kembaliBukuA(String id, String isbn)
    {
      JsonObject hasil = null;
      String output = null;
      try {
        URL url = new URL(urlPerpusA + "Kembali?id=" + id + "&isbn=" + isbn);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200)
        {
          throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
        }

        InputStream is = conn.getInputStream();


        JsonReader jsonReader = Json.createReader(is);

        JsonObject jsonObject = jsonReader.readObject();

        hasil = jsonObject;
        output = hasil.getString("Hasil");

        jsonReader.close();
        is.close();
        conn.disconnect();
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      return output;
    }
    
    public static String kembaliBukuB(String id, String isbn)
    {
      JsonObject hasil = null;
      String output = null;
      try {
        URL url = new URL(urlPerpusB + "Kembali?id=" + id + "&isbn=" + isbn);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200)
        {
          throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
        }

        InputStream is = conn.getInputStream();


        JsonReader jsonReader = Json.createReader(is);


        JsonObject jsonObject = jsonReader.readObject();


        hasil = jsonObject;
        output = hasil.getString("Hasil");



        jsonReader.close();
        is.close();
        conn.disconnect();
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      return output;
    }

    public static Pinjam[] cekHistory(Anggota agt) throws Exception{
        Pinjam[] peminjaman = null;
        JsonObject[] peminjamanJsonA = null;
        JsonObject[] peminjamanJsonB = null;
        URL url;
        HttpURLConnection conn;
        InputStream is;
        JsonReader jsonReader;
        JsonObject jsonObject;
        JsonArray peminjamanJsonArray;
        int index;

//            url = new URL("http://localhost:47448/TestJSON/webresources/A/" + "getHistory?id=" + agt.getIdB());
            url = new URL(urlPerpusA + "getHistory?id=" + agt.getIdA());
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
            {
              throw new RuntimeException("Error Perpustakaan A : HTTP error code " + conn.getResponseCode());
            }

            is = conn.getInputStream();


            jsonReader = Json.createReader(is);
            jsonObject = jsonReader.readObject();


            jsonReader.close();
            is.close();
            conn.disconnect();

            peminjamanJsonArray = jsonObject.getJsonArray("history");
            peminjamanJsonA = new JsonObject[peminjamanJsonArray.size()];
            index = 0;
            for (JsonValue value : peminjamanJsonArray) {
              peminjamanJsonA[(index++)] = ((JsonObject)value);
            } 

            //////
            
//            url = new URL("http://localhost:47448/TestJSON/webresources/B/" + "getHistory?id=" + agt.getIdB());
            url = new URL(urlPerpusB + "getHistory?id=" + agt.getIdB());
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
            {
              throw new RuntimeException("Error Perpustakaan B : HTTP error code " + conn.getResponseCode());
            }

            is = conn.getInputStream();


            jsonReader = Json.createReader(is);
            jsonObject = jsonReader.readObject();


            jsonReader.close();
            is.close();
            conn.disconnect();

            peminjamanJsonArray = jsonObject.getJsonArray("history");
            peminjamanJsonB = new JsonObject[peminjamanJsonArray.size()];
            index = 0;
            for (JsonValue value : peminjamanJsonArray) {
              peminjamanJsonB[(index++)] = ((JsonObject)value);
            } 
        

        peminjaman = new Pinjam[peminjamanJsonA.length+peminjamanJsonB.length];
        for (int i = 0; i < peminjamanJsonA.length; i++) {
            peminjaman[i] = new Pinjam();
            peminjaman[i].setTanggal_pinjam(peminjamanJsonA[i].getString("tgl_pinjam"));
            peminjaman[i].setTanggal_kembali(peminjamanJsonA[i].getString("tgl_kembali"));
            peminjaman[i].setID_Buku(peminjamanJsonA[i].getString("isbn"));
            peminjaman[i].setJudul(peminjamanJsonA[i].getString("judul"));
            peminjaman[i].setStatus(peminjamanJsonA[i].getString("status"));
            peminjaman[i].setPerpustakaan("Perpustakaan A");
        }

        for (int i = peminjamanJsonA.length; i < peminjaman.length; i++) {
            peminjaman[i] = new Pinjam();
            peminjaman[i].setTanggal_pinjam(peminjamanJsonB[i-peminjamanJsonA.length].getString("tgl_pinjam"));
            peminjaman[i].setTanggal_kembali(peminjamanJsonB[i-peminjamanJsonA.length].getString("tgl_kembali"));
            peminjaman[i].setID_Buku(peminjamanJsonB[i-peminjamanJsonA.length].getString("isbn"));
            peminjaman[i].setJudul(peminjamanJsonB[i-peminjamanJsonA.length].getString("judul"));
            peminjaman[i].setStatus(peminjamanJsonB[i-peminjamanJsonA.length].getString("status"));
            peminjaman[i].setPerpustakaan("Perpustakaan B");
        }

        return peminjaman;
    }

    
}
