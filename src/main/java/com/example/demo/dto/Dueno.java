package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
@Data
public class Dueno implements  Serializable {

    private static  final long serialVersionUID =1L;

    private int nmid;
    private  String tipid;
    private  int nmtid;
    private  String nomD;
    private  String ciudad;
    private  String direccion;
    private int  telefono;

    @JsonIgnore
    public void setDuenoFromRs(ResultSet rs)  throws SQLException {
        nmid = rs.getInt("nmid");
        tipid = rs.getString("tipid");
        nmtid = rs.getInt("nmtid");
        nomD = rs.getString("nomD");
        ciudad = rs.getString("ciudad");
        direccion= rs.getString("direccion");
        telefono= rs.getInt("telefono");

    }
}
