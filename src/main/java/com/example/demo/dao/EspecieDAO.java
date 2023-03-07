package com.example.demo.dao;
import com.example.demo.dto.Especie;

import java.util.List;

public interface EspecieDAO {

    List<Especie> getAll();

    Especie getByID(int nmid);

    Especie insert(Especie entity);

    Especie  update(Especie entity);

    Boolean delete(Long id);
}
