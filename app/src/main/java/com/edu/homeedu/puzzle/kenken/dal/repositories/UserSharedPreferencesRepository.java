package com.edu.homeedu.puzzle.kenken.dal.repositories;

import com.edu.homeedu.puzzle.kenken.dal.contracts.UserRepository;
import com.edu.homeedu.puzzle.kenken.dal.datasource.UserSharePreferencesDataSource;
import com.edu.homeedu.puzzle.kenken.models.User;

public class UserSharedPreferencesRepository implements UserRepository {
    private final UserSharePreferencesDataSource userDataSource;

    public UserSharedPreferencesRepository(UserSharePreferencesDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public User findCurrentUser() {
        return userDataSource.fetchCurrentUser();
    }

    @Override
    public void saveCurrentUser(User user, OnUserSavedListener listener) {
        userDataSource.saveCurrentUser(user);
        if (listener != null) {
            listener.onUserSaved(user);
        }
    }
}
