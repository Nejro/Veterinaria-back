package com.example.demo.service;

import com.example.demo.dao.VeterinariaDAO;
import com.example.demo.dto.veterinaria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VeterinariaServicesImpl implements VeterinariaService {

    private final Logger log = LoggerFactory.getLogger(VeterinariaServicesImpl.class);

    private final VeterinariaDAO veterinariaDAO;
    public  VeterinariaServicesImpl(VeterinariaDAO veterinariaDAO)  {this.veterinariaDAO = veterinariaDAO;}

    @Override
    @Transactional(readOnly = true)
    public List<veterinaria> findAll(){
       log.debug("Request to get all veterinaria");
       return  veterinariaDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public veterinaria findOne(int nmid){
    log.debug("Request to get veterinaria: {}", nmid );
    return veterinariaDAO.getByID(nmid);
    }

    @Override
    public veterinaria save(veterinaria Veterinaria){
        log.debug("Request to insert veterinaria : {}", Veterinaria);
        return veterinariaDAO.insert(Veterinaria);
    }
    @Override
    public veterinaria update(veterinaria Veterinaria){
        log.debug("Request to get all veterinaria : {}", Veterinaria);
        return veterinariaDAO.update(Veterinaria);
    }
    @Override
    public void delete(long id) {
        log.debug("Request to delete veterinaria : {}", id);
        veterinariaDAO.delete(id);
    }
}
