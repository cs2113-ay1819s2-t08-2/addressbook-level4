package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.*;

/**
 * API of the Storage component
 */
public interface Storage extends ContactListStorage, UserPrefsStorage,
        TaskListStorage, WorkoutBookStorage, ExpenditureListStorage, TickedTaskListStorage, HabitTrackerListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /* -----------------------Contact List ---------------------------------------------*/
    @Override
    Path getContactListFilePath();


    @Override
    Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException;

    @Override
    void saveContactList(ReadOnlyContactList contactList) throws IOException;

    /* -----------------------Task List ---------------------------------------------*/

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveTaskList (ReadOnlyTaskList taskList) throws IOException;

    /* ----------------------Workout Book ----------------------------------------------*/

    @Override
    Path getWorkoutBookFilePath();

    @Override
    Optional<ReadOnlyWorkoutBook> readWorkoutBook(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveWorkoutBook(ReadOnlyWorkoutBook workoutBook) throws IOException;


    /* -----------------------Expenditure List ---------------------------------------------*/

    @Override
    Path getExpenditureListFilePath();


    @Override
    Optional<ReadOnlyExpenditureList> readExpenditureList() throws DataConversionException, IOException;

    @Override
    void saveExpenditureList(ReadOnlyExpenditureList expenditureList) throws IOException;

    /* ------------------------Habit Tracker List--------------------------------------------*/

    @Override
    Path getHabitTrackerListFilePath();

    @Override
    Optional<ReadOnlyHabitTrackerList> readHabitTrackerList() throws DataConversionException, IOException;

    @Override
    void saveHabitTrackerList(ReadOnlyHabitTrackerList habitTrackerList) throws IOException;

}
