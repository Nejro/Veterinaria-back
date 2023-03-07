package com.example.demo.service;

import com.example.demo.dto.Paciente;

import java.util.List;

public interface PacienteService {
    List<Paciente> findAll();

    Paciente findOne(int nmid);

    Paciente save(Paciente application);

    Paciente update(Paciente application);

    void delete(long id);
}
