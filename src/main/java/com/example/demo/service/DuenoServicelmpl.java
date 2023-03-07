package com.example.demo.service;

import com.example.demo.dao.DuenoDAO;

import com.example.demo.dto.Dueno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DuenoServicelmpl implements DuenoService{
    private final Logger log = LoggerFactory.getLogger(VeterinariaServicesImpl.class);

    private final DuenoDAO duenoDAO;
    public  DuenoServicelmpl(DuenoDAO duenoDAO)  {this.duenoDAO= duenoDAO;}

    @Override
    @Transactional(readOnly = true)
    public List<Dueno> findAll(){
        log.debug("Request to get all dueno");
        return  duenoDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Dueno findOne(int nmid){
        log.debug("Request to get dueno: {}", nmid );
        return duenoDAO.getByID(nmid);
    }

    @Override
    public Dueno save(Dueno dueno){
        log.debug("Request to insert dueno : {}", dueno);
        return duenoDAO.insert(dueno);
    }
    @Override
    public Dueno update(Dueno dueno){
        log.debug("Request to get all dueno : {}", dueno);
        return duenoDAO.update(dueno);
    }

    @Override
    public void delete(long id) {
        log.debug("Request to delete dueno : {}", id);
        duenoDAO.delete(id);
    }
}
