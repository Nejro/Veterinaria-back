package com.example.demo.service;

import com.example.demo.dao.EspecieDAO;
import com.example.demo.dto.Especie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class EspecieServicelmpl implements  EspecieService {

    private final Logger log = LoggerFactory.getLogger(EspecieServicelmpl.class);

    private final EspecieDAO especieDAO;

    public EspecieServicelmpl(EspecieDAO especieDAO) {
        this.especieDAO = especieDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Especie> findAll() {
        log.debug("Request to get all especie");
        return especieDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Especie findOne(int nmid) {
        log.debug("Request to get especie: {}", nmid);
        return especieDAO.getByID(nmid);
    }

    @Override
    public Especie save(Especie especie) {
        log.debug("Request to insert especie : {}", especie);
        return especieDAO.insert(especie);
    }

    @Override
    public Especie update(Especie especie) {
        log.debug("Request to get all especie : {}", especie);
        return especieDAO.update(especie);
    }

    @Override
    public void delete(long id) {
        log.debug("Request to delete paciente : {}", id);
        especieDAO.delete(id);
    }
}
