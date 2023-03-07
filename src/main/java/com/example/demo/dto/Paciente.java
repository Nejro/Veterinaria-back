package com.example.demo.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
@Data
public class Paciente implements  Serializable {
    private static  final long serialVersionUID =1L;

    private  int nmid;
    private  int nmid_dueno;
    private int nmid_raza;
    private int nmid_esp;
    private  String nompet;
    private Date fecNac;
    private  Date fecReg;

    @JsonIgnore
    public void setPacienteFromRs(ResultSet rs)  throws SQLException {
        nmid = rs.getInt("nmid");
        nmid_dueno=rs.getInt("nmid_dueno");
        nmid_raza=rs.getInt("nmid_raza");
        nmid_esp= rs.getInt("nmid_esp");
        nompet=rs.getString("nompet");
        fecNac=rs.getDate("fecNac");
        fecReg=rs.getDate("fecReg");
    }

}



