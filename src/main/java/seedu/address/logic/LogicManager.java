package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LifeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.habit.Habit;
import seedu.address.model.person.Person;
import seedu.address.model.purchase.Purchase;
import seedu.address.model.task.Task;
import seedu.address.model.workout.Workout;
import seedu.address.storage.Storage;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyExpenditureList;
import seedu.address.model.ReadOnlyHabitTrackerList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyWorkoutBook;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private final LifeParser lifeParser;
    private boolean contactListModified;
    private boolean taskListModified;
    private boolean tickedTaskListModified;
    private boolean expenditureListModified;
    private boolean workoutBookModified;
    private boolean habitTrackerListModified;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        history = new CommandHistory();
        lifeParser = new LifeParser();


        // Set contactListModified to true whenever the models' contact list is modified.
        model.getContactList().addListener(observable -> contactListModified = true);
        model.getTaskList().addListener(observable -> taskListModified = true);
        model.getTickedTaskList().addListener(observable -> tickedTaskListModified = true);
        model.getExpenditureList().addListener(observable -> expenditureListModified = true);
        model.getWorkoutList().addListener(observable -> workoutBookModified = true);
        model.getHabitTrackerList().addListener(observable -> habitTrackerListModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        contactListModified = false;
        taskListModified = false;
        tickedTaskListModified = false;
        expenditureListModified = false;
        workoutBookModified = false;
        habitTrackerListModified = false;

        CommandResult commandResult;
        try {
            Command command = lifeParser.parseCommand(commandText);
            commandResult = command.execute(model, history);
        } finally {
            history.add(commandText);
        }
        if (taskListModified) {
            logger.info("Task list modified, saving to file.");
            try {
                storage.saveTaskList(model.getTaskList());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        if (tickedTaskListModified) {
            logger.info("Ticked Task List modified. Saving to file. ");
        } //TODO

        if (expenditureListModified) {
            logger.info("Expenditure list modified, saving to file.");
            try {
                storage.saveExpenditureList(model.getExpenditureList());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }
        if (habitTrackerListModified) {
            logger.info("Habit Tracker List modified, saving to file.");
            try {
                storage.saveHabitTrackerList(model.getHabitTrackerList());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }
        if (workoutBookModified) {
            logger.info("Workout list modified, saving to file.");
            try {
                storage.saveWorkoutBook(model.getWorkoutList());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        if (contactListModified) {
            logger.info("Contact List modified, saving to file.");
            try {
                storage.saveContactList(model.getContactList());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    @Override
    public ReadOnlyContactList getContactList() {

        return model.getContactList();
    }

    @Override
    public ReadOnlyHabitTrackerList getHabitTrackerList() {

        return model.getHabitTrackerList();

    }

    @Override
    public ReadOnlyTaskList getTaskList() {

        return model.getTaskList();
    }

    @Override
    public ReadOnlyTaskList getTickedTaskList() {

        return model.getTickedTaskList();
    }

    @Override
    public ReadOnlyExpenditureList getExpenditureList() {

        return model.getExpenditureList();
    }

    @Override
    public ReadOnlyWorkoutBook getWorkoutList() {

        return model.getWorkoutList();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {

        return model.getFilteredPersonList();

    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {

        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Habit> getFilteredHabitList() {

        return model.getFilteredHabitList();

    }

    @Override
    public ObservableList<Task> getFilteredTickedTaskList() {

        return model.getFilteredTickedTaskList();
    }

    @Override
    public ObservableList<Purchase> getFilteredPurchaseList() {

        return model.getFilteredPurchaseList();
    }

    @Override
    public ObservableList<Workout> getFilteredWorkoutList() {

        return model.getFilteredWorkoutList();
    }

    @Override
    public ObservableList<String> getHistory() {

        return history.getHistory();
    }

    @Override
    public Path getContactListFilePath() {

        return model.getContactListFilePath();
    }

    @Override
    public Path getExpenditureListFilePath() {

        return model.getExpenditureListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {

        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

        model.setGuiSettings(guiSettings);
    }

    @Override
    public ReadOnlyProperty<Person> selectedPersonProperty() {

        return model.selectedPersonProperty();

    }

    @Override
    public ReadOnlyProperty<Task> selectedTaskProperty() {

        return model.selectedTaskProperty();
    }
    @Override
    public ReadOnlyProperty<Workout> selectedWorkoutProperty() {

        return model.selectedWorkoutProperty();
    }

    @Override
    public ReadOnlyProperty<Habit> selectedHabitProperty() {

        return model.selectedHabitProperty();
    }

    @Override
    public ReadOnlyProperty<Purchase> selectedPurchaseProperty() {

        return model.selectedPurchaseProperty();
    }

    @Override
    public void setSelectedPerson(Person person) {

        model.setSelectedPerson(person);
    }

    @Override
    public void setSelectedTask(Task task) {

        model.setSelectedTask(task);
    }

    @Override
    public void setSelectedPurchase(Purchase purchase) {

        model.setSelectedPurchase(purchase);
    }

    @Override
    public void setSelectedWorkout(Workout workout) {

        model.setSelectedWorkout(workout);
    }

    @Override
    public void setSelectedHabit(Habit habit) {

        model.selectedHabitProperty();
    }
}
