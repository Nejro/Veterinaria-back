package com.example.demo.dao;
import com.example.demo.dto.Raza;
import com.example.demo.mapper.RazaMapper;
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
public class RazaDAOImpl implements RazaDAO {

    private static  final String INSERT ="INSERT INTO raza (nmid,nomRaz) VALUES(?,?)";
    private static final String UPDATE ="UPDATE raza  set  nomRaz= ? WHERE nmid = ?";
    private  static  final  String SELECT1 = "Select * from  raza  AS r ";
    private  static  final  String SELECT2 = SELECT1 + " WHERE r.nmid = ?";
    private static  final String DELETE = "DELETE FROM  raza  where nmid= ?";

    JdbcTemplate jdbcTemplate;
    private CacheManager cacheManager;
    public RazaDAOImpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Raza> getAll() {return  jdbcTemplate.query(SELECT1, new RazaMapper());}


    @Override
    public Raza getByID(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECT2, new RazaMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public  Raza insert(Raza entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getNmid(),
                    entity.getNomRaz()});

            return ps;
        }, keyHolder);
        entity.setNmid(keyHolder.getKey().intValue());
        return  entity;
    }

    @Override
    public Raza update(Raza entity){
        jdbcTemplate.update(UPDATE,
                entity.getNomRaz(),
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
