package com.edu.homeedu.puzzle.kenken.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.Arrays;
import java.util.Collection;

import com.edu.homeedu.puzzle.kenken.dal.datasource.KenkenInMemoryDataSource;
import com.edu.homeedu.puzzle.kenken.dal.datasource.UserSharePreferencesDataSource;
import com.edu.homeedu.puzzle.kenken.dal.contracts.KenkenRepository;
import com.edu.homeedu.puzzle.kenken.dal.repositories.KenkenInMemoryRepository;
import com.edu.homeedu.puzzle.kenken.dal.repositories.UserSharedPreferencesRepository;
import com.edu.homeedu.puzzle.kenken.dal.contracts.UserRepository;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.ConstraintBasedKenkenSolver;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.KenkenSolver;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.constraint.ArcConsistencyConstraint;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.constraint.CageConsistencyConstraint;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.constraint.DualConsistencyConstraint;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.constraint.KenkenEliminatingConstraint;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.initializer.CageConsistencyValuesInitializer;
import com.edu.homeedu.puzzle.kenken.application.kenken.solver.initializer.KenkenValuesInitializer;
import com.edu.homeedu.puzzle.kenken.utils.DefaultGameScoreCalculator;
import com.edu.homeedu.puzzle.kenken.utils.contracts.GameScoreCalculator;

public class ApplicationContainer {
    private final KenkenRepository kenkenRepository;
    private final UserRepository userRepository;

    private final KenkenSolver kenkenSolver;
    private final GameScoreCalculator gameScoreCalculator;

    public ApplicationContainer(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        UserSharePreferencesDataSource userDataSource = new UserSharePreferencesDataSource(sharedPreferences);
        userRepository = new UserSharedPreferencesRepository(userDataSource);

        KenkenInMemoryDataSource kenkenDataSource = new KenkenInMemoryDataSource();
        kenkenRepository = new KenkenInMemoryRepository(kenkenDataSource);

        KenkenValuesInitializer valuesInitializer = new CageConsistencyValuesInitializer();
        Collection<KenkenEliminatingConstraint> constraints = Arrays.asList(
                new ArcConsistencyConstraint(),
                new DualConsistencyConstraint(),
                new CageConsistencyConstraint()
        );
        kenkenSolver = new ConstraintBasedKenkenSolver(valuesInitializer, constraints);

        gameScoreCalculator = new DefaultGameScoreCalculator();
    }

    public KenkenRepository getKenkenRepository() {
        return kenkenRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public KenkenSolver getKenkenSolver() {
        return kenkenSolver;
    }

    public GameScoreCalculator getGameScoreCalculator() {
        return gameScoreCalculator;
    }
}