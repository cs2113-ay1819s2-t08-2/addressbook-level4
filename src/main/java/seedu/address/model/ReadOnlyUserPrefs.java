package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getTaskListFilePath();

    Path getTickedTaskListFilePath();

    Path getExpenditureListFilePath();

    Path getWorkoutBookFilePath();

    Path getHabitTrackerListFilePath();
}
