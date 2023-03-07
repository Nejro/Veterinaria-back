package com.example.demo.dao;

import com.example.demo.dto.veterinaria;
import com.example.demo.mapper.VeterinariaMapper;
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
public class VeterinariaDAOImpl implements VeterinariaDAO {

    private static  final String INSERT ="INSERT INTO paciente (nmid_dueno, nmid_raza, nompet, fecNac, fecReg) VALUES(?,?,?,?,?)";
    private static final String UPDATE ="UPDATE paciente set nmid_dueno = ?, nmid_raza = ?, nompet = ?, fecNac= ?,fecReg = ? WHERE nmid = ?";

    private static  final String SELECT1 = "SELECT * FROM paciente as p inner JOIN raza as r ON p.nmid_raza= r.nmid INNER JOIN dueno as d ON p.nmid_dueno = d.nmid INNER JOIN especie as e ON e.nmid= e.nmid";

    private  static  final  String SELECT2 = SELECT1 + " WHERE p.nmid = ?";


    private static  final String DELETE = "DELETE FROM paciente where nmid= ?";


    JdbcTemplate jdbcTemplate;
    private CacheManager cacheManager;
    public VeterinariaDAOImpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<veterinaria> getAll() {return  jdbcTemplate.query(SELECT1, new VeterinariaMapper());}


    @Override
    public veterinaria getByID(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECT2, new VeterinariaMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public  veterinaria insert(veterinaria entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getNmid_dueno(),
                    entity.getNmid_raza(),
                    entity.getNompet(),
                    entity.getFecNac(),
                    entity.getFecReg()});

            return ps;
        }, keyHolder);
         entity.setNmid(keyHolder.getKey().intValue());
         return  entity;
    }

    @Override
    public veterinaria update(veterinaria entity){
        jdbcTemplate.update(UPDATE,
                entity.getNmid_dueno(),
         entity.getNmid_raza(),
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
