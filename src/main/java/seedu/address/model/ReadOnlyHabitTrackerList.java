package seedu.address.model;


import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.address.model.habit.Habit;

/**
 * Unmodifiable view of the habit tracker list.
 */
public interface ReadOnlyHabitTrackerList extends Observable {

    /**
     * Returns an unmodifiable view of the habit tracker list.
     */
    ObservableList<Habit> getHabitList();
}
