package com.example.demo.mapper;
import com.example.demo.dto.veterinaria;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class VeterinariaMapper implements RowMapper<veterinaria> {
@Override
public veterinaria mapRow(ResultSet resultSet, int i) throws  SQLException{
    veterinaria entity=new veterinaria();
    entity.setNmid(resultSet.getInt("nmid"));
    entity.setNmid_dueno(resultSet.getInt("nmid_dueno"));
    entity.setNmid_raza(resultSet.getInt("nmid_raza"));
    entity.setNompet(resultSet.getString("nompet"));
    entity.setFecNac(resultSet.getDate("fecNac"));
    entity.setFecReg(resultSet.getDate("fecReg"));
    entity.setTipid(resultSet.getString("tipid"));
    entity.setNmtid(resultSet.getInt("nmtid"));
    entity.setNomD(resultSet.getString("nomD"));
    entity.setCiudad(resultSet.getString("ciudad"));
    entity.setDireccion(resultSet.getString("direccion"));
    entity.setTelefono(resultSet.getInt("telefono"));
    entity.setNomRaz(resultSet.getString("nomRaz"));
    entity.setNomEsp(resultSet.getString("nomEsp"));
    entity.setVeterinariaFromRs(resultSet);
    return  entity;
}
}
