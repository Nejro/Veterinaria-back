package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


@Data
public class veterinaria  implements Serializable {

    private static  final long serialVersionUID =1L;

    private int nmid;
    private int nmid_dueno;
    private  int nmid_raza;
    private  String nompet;
    private Date fecNac;
    private  Date fecReg;
    private  String tipid;
    private  int nmtid;
    private  String nomD;
    private  String ciudad;
    private  String direccion;
    private int  telefono;
    private String nomRaz;
    private String nomEsp;

    @JsonIgnore
    public void setVeterinariaFromRs(ResultSet rs)  throws SQLException {
    nmid=rs.getInt("nmid");
    nmid_dueno=rs.getInt("nmid_dueno");
    nmid_raza=rs.getInt("nmid_raza");
    nompet=rs.getString("nompet");
    fecNac=rs.getDate("fecNac");
    fecReg=rs.getDate("fecReg");
    tipid=rs.getString("tipid");
    nmtid=rs.getInt("nmtid");
    nomD=rs.getString("nomD");
    ciudad=rs.getString("ciudad");
    direccion=rs.getString("direccion");
    telefono=rs.getInt("telefono");
    nomRaz=rs.getString("nomRaz");
    nomEsp=rs.getString("nomEsp");

    }
}
