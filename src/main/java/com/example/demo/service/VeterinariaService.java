package com.example.demo.service;

import com.example.demo.dto.veterinaria;

import java.util.List;
public interface VeterinariaService {

    List<veterinaria> findAll();

    veterinaria findOne(int nmid);

    veterinaria save(veterinaria application);
    veterinaria update(veterinaria application);

    void delete(long id);


}
