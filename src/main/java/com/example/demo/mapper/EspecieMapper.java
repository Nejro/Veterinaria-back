package com.example.demo.mapper;
import com.example.demo.dto.Especie;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EspecieMapper implements  RowMapper<Especie> {
    @Override
    public Especie mapRow(ResultSet resultSet, int i) throws SQLException {
        Especie entity = new Especie();
        entity.setNmid(resultSet.getInt("nmid"));
        entity.setNomEsp(resultSet.getString("nomEsp"));
        entity.setEspecieFromRs(resultSet);
        return  entity;
    }
}
