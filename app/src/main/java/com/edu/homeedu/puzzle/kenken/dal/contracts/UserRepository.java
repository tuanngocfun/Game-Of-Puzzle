package com.edu.homeedu.puzzle.kenken.dal.contracts;

import com.edu.homeedu.puzzle.kenken.models.User;

public interface UserRepository {
    interface OnUserSavedListener {
        void onUserSaved(User user);
    }

    User findCurrentUser();
    void saveCurrentUser(User user, OnUserSavedListener listener);
}
