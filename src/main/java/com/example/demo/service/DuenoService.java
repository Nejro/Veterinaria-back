package com.example.demo.service;

import com.example.demo.dto.Dueno;


import java.util.List;

public interface DuenoService {

    List<Dueno> findAll();

    Dueno findOne(int nmid);

    Dueno save(Dueno application);

    Dueno update(Dueno application);

    void delete(long id);

}
