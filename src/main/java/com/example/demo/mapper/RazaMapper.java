package com.example.demo.mapper;
import com.example.demo.dto.Raza;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class RazaMapper  implements RowMapper<Raza>{
    @Override
    public Raza mapRow(ResultSet resultSet, int i) throws SQLException {
        Raza entity = new Raza();
        entity.setNmid(resultSet.getInt("nmid"));
        entity.setNomRaz(resultSet.getString("nomRaz"));
        entity.setRazaFromRs(resultSet);
        return  entity;
    }
}
