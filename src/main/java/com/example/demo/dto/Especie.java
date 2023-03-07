package com.example.demo.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
@Data
public class Especie  implements  Serializable {
    private static  final long serialVersionUID =1L;
    private int nmid;
    private  String nomEsp;

    @JsonIgnore
    public void setEspecieFromRs(ResultSet rs)  throws SQLException {
        nmid = rs.getInt("nmid");
        nomEsp=rs.getString("nomEsp");
    }
}
