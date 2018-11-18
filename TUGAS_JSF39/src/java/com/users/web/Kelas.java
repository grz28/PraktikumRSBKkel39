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
@ManagedBean(name = "Kelas")
@SessionScoped
public class Kelas {
        
    private String id_jurusan;
    public void setId_jurusan(String id_jurusan) {
        this.id_jurusan = id_jurusan;
    }
    public String getId_jurusan() {
        return id_jurusan;
    }

    private String jurusan;
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
    public String getJurusan() {
        return jurusan;
    }
    
    private String wali;
    public void setWali(String wali) {
        this.wali = wali;
    }
    public String getWali() {
        return wali;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_Kelas(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_idjurusan = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from kelas where id_jurusan="+Field_idjurusan);
          Kelas obj_kelas = new Kelas();
          rs.next();
          obj_kelas.setId_jurusan(rs.getString("id_jurusan"));
          obj_kelas.setJurusan(rs.getString("jurusan"));
          obj_kelas.setWali(rs.getString("wali"));
          sessionMap.put("EditKelas", obj_kelas);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit_kelas.xhtml?faces-redirect=true";   
}

public String Delete_Kelas(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_idjurusan = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from kelas where id_jurusan=?");
         ps.setString(1, Field_idjurusan);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/kelas.xhtml?faces-redirect=true";   
}

public String Update_Kelas(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_idkelas= params.get("Update_idkelas");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update kelas set id_jurusan=?, jurusan=?, wali=? where id_jurusan=?");
            ps.setString(1, id_jurusan);
            ps.setString(2, jurusan);
            ps.setString(3, wali);
            ps.setString(4, Update_idkelas);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/kelas.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_kelas() throws Exception{
        ArrayList list_of_kelas=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from kelas");
            while(rs.next()){
                Kelas obj_kelas = new Kelas();
                obj_kelas.setId_jurusan(rs.getString("id_jurusan"));
                obj_kelas.setJurusan(rs.getString("jurusan"));
                obj_kelas.setWali(rs.getString("wali"));
                list_of_kelas.add(obj_kelas);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_kelas;
}
    
public String Tambah_Kelas(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into kelas(id_jurusan, jurusan, wali) value('"+id_jurusan+"','"+jurusan+"','"+wali+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/kelas.xhtml?faces-redirect=true";
    }
    
    public Kelas() {
    }
    
        
    }
    

