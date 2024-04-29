package com.edu.homeedu.puzzle.kenken.dal.contracts;

import java.util.List;

import com.edu.homeedu.puzzle.kenken.models.Kenken;

public interface KenkenRepository {
    List<Kenken> findAll();
    Kenken findOneById(int id);
}
