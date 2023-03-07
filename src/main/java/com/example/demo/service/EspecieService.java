package com.example.demo.service;


import com.example.demo.dto.Especie;

import java.util.List;

public interface EspecieService {
    List<Especie> findAll();

    Especie findOne(int nmid);

    Especie save(Especie application);

    Especie update(Especie application);

    void delete(long id);
}
