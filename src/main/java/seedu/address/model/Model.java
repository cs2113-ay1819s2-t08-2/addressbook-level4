package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.habit.Habit;
import seedu.address.model.person.Person;
import seedu.address.model.purchase.Purchase;
import seedu.address.model.task.Task;
import seedu.address.model.workout.Workout;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' contact list file path.
     */
    Path getContactListFilePath();

    /**
     * Sets the user prefs' contact list file path.
     */
    void setContactListFilePath(Path contactListFilePath);

    /**
     * Replaces contact list data with the data in {@code contactList}.
     */
    void setContactList(ReadOnlyContactList contactList);

    /** Returns the ContactList */
    ReadOnlyContactList getContactList();

    ReadOnlyTaskList getTaskList();

    ReadOnlyTaskList getTickedTaskList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contact list.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the contact list.
     */
    void deletePerson(Person target);


    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    void addTask(Task task);

    void addTickedTaskList(Task task);

    boolean hasTask(Task task);

    void deleteTask(Task task);

    void sortTask();

    void commitTaskList();

    void commitTickedTaskList();
    /**
     * Adds the given person.
     * {@code person} must not already exist in the contact list.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the contact list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contact list.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

     void updateFilteredTaskList(Predicate<Task> predicate);
    /**
     * Updates the filter of the filtered ticked task list by the given {@code predicate}.
     * @throws NullPointerException IF {@code predicate} is null;
     */
    void updateFilteredTickedTaskList(Predicate<Task> predicate);

    /**
     * Returns true if the model has previous contact list states to restore.
     */
    boolean canUndoContactList();

    /**
     * Returns true if the model has undone contact list states to restore.
     */
    boolean canRedoContactList();

    /**
     * Restores the model's contact list to its previous state.
     */
    void undoContactList();

    /**
     * Restores the model's contact list to its previously undone state.
     */
    void redoContactList();

    /**
     * Saves the current contact list state for undo/redo.
     */
    void commitContactList();

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Person> selectedPersonProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    Person getSelectedPerson();

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedPerson(Person person);

    public ObservableList<Task> getFilteredTaskList();

    public ObservableList<Task> getFilteredTickedTaskList();

    public ReadOnlyProperty<Task> selectedTaskProperty();

    void setSelectedTask(Task task);

    void setTask(Task target, Task editedTask);

    Task getSelectedTask();



    /** {@code Predicate} that always evaluate to true */
    Predicate<Purchase> PREDICATE_SHOW_ALL_PURCHASES = unused -> true;

    /**
     * Replaces expenditure list with the data in {@code expenditureList}.
     */
    void setExpenditureList(ReadOnlyExpenditureList expenditureList);

    /** Returns the ExpenditureList */
    ReadOnlyExpenditureList getExpenditureList();

    /**
     * Adds the given purchase.
     */
    void addPurchase(Purchase purchase);

    /** Returns an unmodifiable view of the filtered purchase list */
    ObservableList<Purchase> getFilteredPurchaseList();

    /**
     * Updates the filter of the filtered purchase list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPurchaseList(Predicate<Purchase> predicate);


    /**
     * Saves the current expenditure list state for undo/redo.
     */
    void commitExpenditureList();

    /**
     * Selected purchase in the filtered purchase list.
     * null if no purchase is selected.
     */
    ReadOnlyProperty<Purchase> selectedPurchaseProperty();

    /**
     * Returns the selected purchase in the filtered purchase list.
     * null if no purchase is selected.
     */
    Purchase getSelectedPurchase();

    /**
     * Sets the selected purchase in the filtered purchase list.
     */
    void setSelectedPurchase(Purchase purchase);

    void addWorkout(Workout workout);

    void commitWorkoutBook();

    void setSelectedWorkout(Workout workout);

    ReadOnlyProperty<Workout> selectedWorkoutProperty();

    ObservableList<Workout> getFilteredWorkoutList();

    ReadOnlyWorkoutBook getWorkoutList();


    void updateFilteredWorkoutList(Predicate<Workout> predicate);

    void setWorkoutBook(ReadOnlyWorkoutBook workoutBook);
  
    ArrayList<Workout> getRecent();

    /** {@code Predicate} that always evaluate to true */
    Predicate<Habit> PREDICATE_SHOW_ALL_HABIT = unused -> true;

    /**
     * Replaces habit tracker list with the data in {@code habitTrackerList}.
     */
    void setHabitTrackerList(ReadOnlyHabitTrackerList habitTrackerList);

    /** Returns the HabitTrackerList */
    ReadOnlyHabitTrackerList getHabitTrackerList();

    /**
     * Adds the given habit.
     */
    void addHabit(Habit habit);

    /** Returns an unmodifiable view of the filtered purchase list */
    ObservableList<Habit> getFilteredHabitList();

    /**
     * Updates the filter of the filtered habit list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHabitList(Predicate<Habit> predicate);


    /**
     * Saves the current habit tracker list state for undo/redo.
     */
    void commitHabitTrackerList();

    /**
     * Selected habit in the filtered habit list.
     * null if no habit is selected.
     */
    ReadOnlyProperty<Habit> selectedHabitProperty();

    /**
     * Returns the selected habit in the filtered habit list.
     * null if no purchase is selected.
     */
    Habit getSelectedHabit();

    /**
     * Sets the selected habit in the filtered habit list.
     */
    void setSelectedHabit(Habit habit);

}
