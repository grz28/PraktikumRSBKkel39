/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
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
@ManagedBean(name = "Join")
@SessionScoped
public class Join {

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

    public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select siswa.*,kelas.jurusan,kelas.wali from siswa left join kelas on siswa.id_jurusan = kelas.id_jurusan order by siswa.id_siswa asc");
            while(rs.next()){
                Join obj_Join = new Join();
                obj_Join.setId_siswa(rs.getString("id_siswa"));
                obj_Join.setNama(rs.getString("nama"));
                obj_Join.setJurusan(rs.getString("jurusan"));
                obj_Join.setWali(rs.getString("wali"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
}
    
    public Join() {
    }
    
}
