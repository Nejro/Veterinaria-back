package com.example.demo.dao;

import com.example.demo.dto.veterinaria;


import java.util.List;
public interface   VeterinariaDAO {

    List<veterinaria> getAll();

    veterinaria getByID(int nmid);

    veterinaria insert(veterinaria entity);

    veterinaria update(veterinaria entity);

    Boolean delete(Long id);

    }


