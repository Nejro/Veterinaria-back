package com.example.demo.service;


import com.example.demo.dao.PacienteDAO;
import com.example.demo.dto.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PacienteServicelmpl implements PacienteService {

    private final Logger log = LoggerFactory.getLogger(EspecieServicelmpl.class);

    private final PacienteDAO pacienteDAO;

    public PacienteServicelmpl(PacienteDAO pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        log.debug("Request to get all paciente");
        return  pacienteDAO.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findOne(int nmid) {
        log.debug("Request to get paciente : {}", nmid);
        return pacienteDAO.getByID(nmid);
    }

    @Override
    public Paciente save(Paciente paciente) {
        log.debug("Request to insert paciente : {}", paciente);
        return pacienteDAO.insert(paciente);
    }

    @Override
    public Paciente update(Paciente paciente) {
        log.debug("Request to get all paciente : {}", paciente);
        return pacienteDAO.update(paciente);
    }

    @Override
    public void delete(long id) {
        log.debug("Request to delete paciente : {}", id);
        pacienteDAO.delete(id);
    }
}
