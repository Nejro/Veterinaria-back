package com.example.demo.dao;
import com.example.demo.dto.Dueno;


import java.util.List;

public interface DuenoDAO {
    List<Dueno> getAll();

    Dueno getByID(int nmid);

    Dueno insert(Dueno entity);

    Dueno  update(Dueno entity);

    Boolean delete(Long id);
}
