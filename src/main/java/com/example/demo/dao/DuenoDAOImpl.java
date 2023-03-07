package com.example.demo.dao;
import com.example.demo.dto.Dueno;
import com.example.demo.mapper.DuenoMapper;
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
public class DuenoDAOImpl implements DuenoDAO {

    private static  final String INSERT ="INSERT INTO dueno (nmid, tipid,  nmtid, nomD, ciudad , direccion , telefono) VALUES(?,?,?,?,?,?,?)";

    private static final String UPDATE ="UPDATE dueno  set  tipid = ?, nmtid = ? , nomD= ?, ciudad= ?, direccion= ?,telefono = ? WHERE nmid = ?";
    private  static  final  String SELECT1 = "Select * from  dueno AS d ";
    private  static  final  String SELECT2 = SELECT1 + " WHERE d.nmid = ?";
    private static  final String DELETE = "DELETE FROM  dueno where nmid= ?";


    JdbcTemplate jdbcTemplate;
    private CacheManager cacheManager;
    public DuenoDAOImpl(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public List<Dueno> getAll() {return  jdbcTemplate.query(SELECT1, new DuenoMapper());}


    @Override
    public Dueno getByID(int nmid){
        try{
            return jdbcTemplate.queryForObject(SELECT2, new DuenoMapper(),nmid);
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    @Override
    public  Dueno insert(Dueno entity){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"nmdato"});
            DaoUtil.setPrepareStatement(ps, new Object[]{
                    entity.getNmid(),
                    entity.getTipid(),
                    entity.getNmtid(),
                    entity.getNomD(),
                    entity.getCiudad(),
                    entity.getDireccion(),
                    entity.getTelefono()});

            return ps;
        }, keyHolder);
        entity.setNmid(keyHolder.getKey().intValue());
        return  entity;
    }

    @Override
    public Dueno update(Dueno entity){
        jdbcTemplate.update(UPDATE,
                entity.getTipid(),
                entity.getNmtid(),
                entity.getNomD(),
                entity.getCiudad(),
                entity.getDireccion(),
                entity.getTelefono(),
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
