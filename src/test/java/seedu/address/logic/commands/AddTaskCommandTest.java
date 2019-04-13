package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.habit.Habit;
import seedu.address.model.person.Person;
import seedu.address.model.purchase.Purchase;
import seedu.address.model.task.Task;
import seedu.address.model.workout.Workout;
import seedu.address.testutil.TaskBuilder;





public class AddTaskCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddTaskCommand(null);
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub, commandHistory);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() throws Exception {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);
        ModelStub modelStub = new ModelStubWithTask(validTask);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddTaskCommand.MESSAGE_DUPLICATE_TASK);
        addTaskCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Task taskOne = new TaskBuilder().withTaskName("taskOne").build();
        Task taskTwo = new TaskBuilder().withTaskName("taskTwo").build();
        AddTaskCommand addTaskOneCommand = new AddTaskCommand(taskOne);
        AddTaskCommand addTaskTwoCommand = new AddTaskCommand(taskTwo);

        //same object -> returns true
        assertTrue(addTaskOneCommand.equals(addTaskOneCommand));

        //same values -> returns true
        AddTaskCommand addTaskOneCommandCopy = new AddTaskCommand(taskOne);
        assertTrue(addTaskOneCommand.equals(addTaskOneCommandCopy));

        // different types -> return false
        assertFalse(addTaskOneCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskOneCommand.equals(null));

        // different task -> returns false
        assertFalse(addTaskOneCommand.equals(addTaskTwoCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    public class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getContactListFilePath() {
            return null;
        }

        @Override
        public void setContactListFilePath(Path contactListFilePath) {

        }

        @Override
        public void setContactList(ReadOnlyContactList contactList) {

        }


        @Override
        public ReadOnlyContactList getContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            return null;
        }

        @Override
        public ReadOnlyTaskList getTickedTaskList() {
            return null;
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {

        }

        @Override
        public void addTickedTaskList(Task task) {

        }

        @Override
        public boolean hasTask(Task task) {
            return false;
        }

        @Override
        public void deleteTask(Task task) {

        }

        @Override
        public void sortTask() {

        }

        @Override
        public void commitTaskList() {

        }

        @Override
        public void commitTickedTaskList() {

        }

        @Override
        public void addPerson(Person person) {

        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {

        }

        @Override
        public void updateFilteredTickedTaskList(Predicate<Task> predicate) {

        }

        @Override
        public boolean canUndoContactList() {
            return false;
        }

        @Override
        public boolean canRedoContactList() {
            return false;
        }

        @Override
        public void undoContactList() {

        }

        @Override
        public void redoContactList() {

        }

        @Override
        public void commitContactList() {

        }

        @Override
        public ReadOnlyProperty<Person> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getSelectedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return null;
        }

        @Override
        public ObservableList<Task> getFilteredTickedTaskList() {
            return null;
        }

        @Override
        public ReadOnlyProperty<Task> selectedTaskProperty() {
            return null;
        }

        @Override
        public void setSelectedTask(Task task) {

        }

        @Override
        public void setTask(Task target, Task editedTask) {

        }

        @Override
        public Task getSelectedTask() {
            return null;
        }

        @Override
        public void addPurchase(Purchase purchase) {

        }

        @Override
        public void setExpenditureList(ReadOnlyExpenditureList newData) {

        }

        @Override
        public ReadOnlyExpenditureList getExpenditureList() {
            return null;
        }


        @Override
        public ObservableList<Purchase> getFilteredPurchaseList() {
            return null;
        }

        @Override
        public void updateFilteredPurchaseList(Predicate<Purchase> predicate) {

        }

        @Override
        public void commitExpenditureList() {

        }

        @Override
        public ReadOnlyProperty<Purchase> selectedPurchaseProperty() {
            return null;
        }

        @Override
        public void setSelectedPurchase(Purchase purchase) {

        }

        @Override
        public void addWorkout(Workout workout) {

        }

        @Override
        public ArrayList<Workout> getRecent() {
            return null;
        }
        @Override
        public void commitWorkoutBook() {

        }

        @Override
        public void setSelectedWorkout(Workout workout) {

        }

        @Override
        public ReadOnlyProperty<Workout> selectedWorkoutProperty() {
            return null;
        }

        @Override
        public ObservableList<Workout> getFilteredWorkoutList() {
            return null;
        }

        @Override
        public ReadOnlyWorkoutBook getWorkoutList() {
            return null;
        }

        @Override
        public void updateFilteredWorkoutList(Predicate<Workout> predicate) {

        }

        @Override
        public void setWorkoutBook(ReadOnlyWorkoutBook workoutBook) {

        }

        @Override
        public void setHabitTrackerList(ReadOnlyHabitTrackerList habitTrackerList) {

        }

        @Override
        public ReadOnlyHabitTrackerList getHabitTrackerList() {
            return null;
        }

        @Override
        public void addHabit(Habit habit) {

        }

        @Override
        public ObservableList<Habit> getFilteredHabitList() {
            return null;
        }

        @Override
        public void updateFilteredHabitList(Predicate<Habit> predicate) {

        }

        @Override
        public void commitHabitTrackerList() {

        }

        @Override
        public ReadOnlyProperty<Habit> selectedHabitProperty() {
            return null;
        }

        @Override
        public Habit getSelectedHabit() {
            return null;
        }

        @Override
        public void setSelectedHabit(Habit habit) {

        }

        @Override
        public void deleteHabit(Habit habit) {

        }

        @Override
        public Purchase getSelectedPurchase() {
            return null;
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {

        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A model stub that always accept the person being added/
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {

        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public void commitTaskList() {

        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            return new TaskList();
        }

    }
}
