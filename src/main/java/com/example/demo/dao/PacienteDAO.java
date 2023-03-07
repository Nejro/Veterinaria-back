package com.example.demo.dao;

import com.example.demo.dto.Paciente;

import java.util.List;

public interface PacienteDAO {

    List<Paciente> getAll();

    Paciente getByID(int nmid);

    Paciente insert(Paciente entity);

    Paciente  update(Paciente entity);

    Boolean delete(Long id);
}
