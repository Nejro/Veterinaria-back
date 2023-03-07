package com.example.demo.mapper;
import com.example.demo.dto.Dueno;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DuenoMapper implements  RowMapper<Dueno> {
    @Override
    public Dueno mapRow(ResultSet resultSet, int i) throws SQLException {
        Dueno entity=new Dueno();
        entity.setNmid(resultSet.getInt("nmid"));
        entity.setTipid(resultSet.getString("tipid"));
        entity.setNmtid(resultSet.getInt("nmtid"));
        entity.setNomD(resultSet.getString("nomD"));
        entity.setCiudad(resultSet.getString("ciudad"));
        entity.setDireccion(resultSet.getString("direccion"));
        entity.setTelefono(resultSet.getInt("telefono"));
        entity.setDuenoFromRs(resultSet);
        return  entity;
    }
}