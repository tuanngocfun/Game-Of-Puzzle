package com.edu.homeedu.puzzle.kenken.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.util.Objects;

import com.edu.homeedu.puzzle.kenken.dal.contracts.UserRepository;
import com.edu.homeedu.puzzle.kenken.di.ApplicationContainer;
import com.edu.homeedu.puzzle.kenken.models.User;
import com.edu.homeedu.puzzle.kenken.utils.helpers.ViewModelHelpers;

/**
 * UserViewModel is a ViewModel that manages user-related data and interactions.
 * It provides the current user's record and allows updating it when a higher score is achieved.
 */
public class UserViewModel extends ViewModel {
    public static final ViewModelInitializer<UserViewModel> initializer = new ViewModelInitializer<>(
            UserViewModel.class,
            creationExtras -> {
                ApplicationContainer container = ViewModelHelpers.getApplicationContainer(creationExtras);
                return new UserViewModel(container.getUserRepository());
            }
    );

    private final UserRepository userRepository;
    private final MutableLiveData<Integer> recordLiveData;

    /**
     * Constructs a new UserViewModel with the provided UserRepository.
     *
     * @param userRepository the UserRepository to interact with user data
     */
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        recordLiveData = new MutableLiveData<>(userRepository.findCurrentUser().getRecord());
    }

    /**
     * Returns the LiveData object holding the current user's record.
     *
     * @return a LiveData<Integer> representing the user's record
     */
    public LiveData<Integer> getRecord() {
        return recordLiveData;
    }

    /**
     * Updates the user's record if the provided score is higher than the current record.
     *
     * @param score the new score to be considered for the record
     */
    public void postScore(int score) {
        int currentRecord = Objects.requireNonNull(recordLiveData.getValue());
        if (score <= currentRecord) {
            return;
        }
        User currentUser = userRepository.findCurrentUser();
        currentUser.setRecord(score);
        userRepository.saveCurrentUser(currentUser, user -> recordLiveData.setValue(user.getRecord()));
    }
}
