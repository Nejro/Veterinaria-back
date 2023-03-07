package com.example.demo.service;


import com.example.demo.dao.RazaDAO;
import com.example.demo.dto.Especie;
import com.example.demo.dto.Raza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RazaServicelmpl implements RazaService {
    private final Logger log = LoggerFactory.getLogger(RazaServicelmpl.class);

    private final RazaDAO razaDAO;

    public RazaServicelmpl(RazaDAO razaDAO) {
        this.razaDAO = razaDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Raza> findAll() {
        log.debug("Request to get all raza");
        return razaDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Raza findOne(int nmid) {
        log.debug("Request to get raza: {}", nmid);
        return razaDAO.getByID(nmid);
    }

    @Override
    public Raza save(Raza raza) {
        log.debug("Request to insert raza : {}", raza);
        return razaDAO.insert(raza);
    }

    @Override
    public Raza update(Raza raza) {
        log.debug("Request to get all raza : {}", raza);
        return razaDAO.update(raza);
    }

    @Override
    public void delete(long id) {
        log.debug("Request to delete raza : {}", id);
        razaDAO.delete(id);
    }
}
