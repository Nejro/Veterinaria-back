package com.example.demo.dao;

import com.example.demo.dto.Raza;

import java.util.List;

public interface RazaDAO {
    List<Raza> getAll();

    Raza getByID(int nmid);

    Raza insert(Raza entity);

    Raza  update(Raza entity);

    Boolean delete(Long id);
}
