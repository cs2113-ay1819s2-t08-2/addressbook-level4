package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.habit.Habit;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 *  Deletes a Habit from the Habit Tracker List.
 */
public class DeleteHabitCommand extends Command {
    public static final String COMMAND_WORD = "delHabit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a habit identified by the index number in the displayed habit tracker list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DELETE_HABIT_SUCCESS = "Deleted habit: %1$s";

    private final Index targetIndex;

    public DeleteHabitCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Habit> lastShownList = model.getFilteredHabitList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_HABIT_DISPLAYED_INDEX);
        }
        Habit habitToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteHabit(habitToDelete);
        model.commitHabitTrackerList();
        return new CommandResult(String.format(MESSAGE_DELETE_HABIT_SUCCESS, habitToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteHabitCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteHabitCommand) other).targetIndex)); // state check
    }
}
