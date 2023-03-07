package com.example.demo.mapper;

import com.example.demo.dto.Paciente;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PacienteMapper  implements  RowMapper<Paciente> {

    @Override
    public Paciente mapRow(ResultSet resultSet, int i) throws SQLException {
        Paciente entity = new Paciente();
        entity.setNmid(resultSet.getInt("nmid"));
        entity.setNmid_dueno(resultSet.getInt("nmid_dueno"));
        entity.setNmid_raza(resultSet.getInt("nmid_raza"));
        entity.setNmid_esp(resultSet.getInt("nmid_esp"));
        entity.setNompet(resultSet.getString("nompet"));
        entity.setFecNac(resultSet.getDate("fecNac"));
        entity.setFecReg(resultSet.getDate("fecReg"));
        entity.setPacienteFromRs(resultSet);
        return entity;
    }
}