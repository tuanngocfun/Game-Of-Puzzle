package com.edu.homeedu.puzzle.kenken.dal.repositories;

import java.util.List;

import com.edu.homeedu.puzzle.kenken.dal.contracts.KenkenRepository;
import com.edu.homeedu.puzzle.kenken.dal.datasource.KenkenInMemoryDataSource;
import com.edu.homeedu.puzzle.kenken.models.Kenken;

public class KenkenInMemoryRepository implements KenkenRepository {
    private final KenkenInMemoryDataSource kenkenDataSource;

    public KenkenInMemoryRepository(KenkenInMemoryDataSource dataSource) {
        this.kenkenDataSource = dataSource;
    }

    @Override
    public List<Kenken> findAll() {
        return kenkenDataSource.fetchAll();
    }

    @Override
    public Kenken findOneById(int id) {
        return kenkenDataSource.fetchById(id);
    }
}
