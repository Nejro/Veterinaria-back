package com.example.demo.service;

import com.example.demo.dto.Raza;

import java.util.List;

public interface RazaService {
    List<Raza> findAll();

    Raza findOne(int nmid);

    Raza save(Raza application);

    Raza update(Raza application);

    void delete(long id);
}
