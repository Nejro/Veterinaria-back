package com.example.demo.dao;
import com.example.demo.dto.Especie;
import com.example.demo.mapper.EspecieMapper;
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
public class EspecieDAOlmpl implements EspecieDAO {

    private static  final String INSERT ="INSERT INTO especie (nmid, nomEsp) VALUES(?,?)";
    private static final String UPDATE ="UPDATE especie  set  nomEsp= ? WHERE nmid = ?";
    private  static  final  String SELECT1 = "Select * from  especie  AS e ";
    private  static  final  String SELECT2 = SELECT1 + " WHERE e.nmid = ?";
    private static  final String DELETE = "DELETE FROM  especie  where nmid= ?";

    JdbcTemplate jdbcTemplate;
    private CacheManager cacheManager;
    public EspecieDAOlmpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Especie> getAll() {return  jdbcTemplate.query(SELECT1, new EspecieMapper());}


    @Override
    public Especie getByID(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECT2, new EspecieMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public  Especie insert(Especie entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getNmid(),
                    entity.getNomEsp()});

            return ps;
        }, keyHolder);
        entity.setNmid(keyHolder.getKey().intValue());
        return  entity;
    }

    @Override
    public Especie update(Especie entity){
        jdbcTemplate.update(UPDATE,
                entity.getNomEsp(),
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
