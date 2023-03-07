package com.example.demo.dao;

import com.example.demo.dto.Paciente;
import com.example.demo.mapper.PacienteMapper;
import com.example.demo.util.DaoUtil;
import org.springframework.cache.CacheManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PacienteDAOlmpl implements  PacienteDAO{

    private static  final String INSERT ="INSERT INTO  paciente (nmid_dueno, nmid_raza,nmid_esp, nompet,fecNac, fecReg) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE ="UPDATE  paciente  set  nmid_dueno= ?, nmid_raza = ?, nmid_esp = ? , nompet = ?, fecNac = ?, fecReg = ?  WHERE nmid = ?";
    private  static  final  String SELECT1 = "SELECT  * from  paciente  AS p ";
    private  static  final  String SELECT2 = SELECT1 + " WHERE p.nmid = ?";
    private static  final String DELETE = "DELETE FROM  paciente  where nmid= ?";


    JdbcTemplate jdbcTemplate;
    private CacheManager cacheManager;
    public PacienteDAOlmpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Paciente> getAll() {return  jdbcTemplate.query(SELECT1, new PacienteMapper());}


    @Override
    public Paciente getByID(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECT2, new PacienteMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public  Paciente insert(Paciente entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getNmid_dueno(),
                    entity.getNmid_raza(),
                    entity.getNmid_esp(),
                    entity.getNompet(),
                    entity.getFecNac(),
                    entity.getFecReg()});

            return ps;
        }, keyHolder);
        entity.setNmid(keyHolder.getKey().intValue());
        return  entity;
    }

    @Override
    public Paciente update(Paciente entity){
        jdbcTemplate.update(UPDATE,
                entity.getNmid_dueno(),
                entity.getNmid_raza(),
                entity.getNmid_esp(),
                entity.getNompet(),
                entity.getFecNac(),
                entity.getFecReg(),
                entity.getNmid());

        return entity;
    }
    @Override
    public Boolean delete(Long id) {
        return jdbcTemplate.update(DELETE,
                id
        )>0;
    }


}
