/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Notebook
 */
@ManagedBean
@SessionScoped
public class Siswa {

    /**
     * Creates a new instance of Siswa
     */
    private String id_siswa;
    public void setId_siswa(String id_siswa) {
        this.id_siswa = id_siswa;
    }
    public String getId_siswa() {
        return id_siswa;
    }

    private String nama;
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }
    
    private String id_jurusan;
    public void setId_jurusan(String id_jurusan) {
        this.id_jurusan = id_jurusan;
    }
    public String getId_jurusan() {
        return id_jurusan;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_Siswa(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_idsiswa = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from siswa where id_siswa="+Field_idsiswa);
          Siswa obj_siswa = new Siswa();
          rs.next();
          obj_siswa.setId_siswa(rs.getString("id_siswa"));
          obj_siswa.setNama(rs.getString("nama"));
          obj_siswa.setId_jurusan(rs.getString("id_jurusan"));
          sessionMap.put("EditSiswa", obj_siswa);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit_siswa.xhtml?faces-redirect=true";   
}

public String Delete_Siswa(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_idsiswa = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from siswa where id_siswa=?");
         ps.setString(1, Field_idsiswa);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}

public String Update_Siswa(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_idsiswa= params.get("Update_idsiswa");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update siswa set id_siswa=?, nama=?, id_jurusan=? where id_siswa=?");
            ps.setString(1, id_siswa);
            ps.setString(2, nama);
            ps.setString(3, id_jurusan);
            ps.setString(4, Update_idsiswa);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_siswa() throws Exception{
        ArrayList list_of_siswa=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from siswa");
            while(rs.next()){
                Siswa obj_Siswa = new Siswa();
                obj_Siswa.setId_siswa(rs.getString("id_siswa"));
                obj_Siswa.setNama(rs.getString("nama"));
                obj_Siswa.setId_jurusan(rs.getString("id_jurusan"));
                list_of_siswa.add(obj_Siswa);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_siswa;
}
    
public String Tambah_Siswa(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into siswa(id_siswa, nama, id_jurusan) value('"+id_siswa+"','"+nama+"','"+id_jurusan+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/index.xhtml?faces-redirect=true";
    }
    public Siswa() {
    }
    
}
