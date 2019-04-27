package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteHabitCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses the given String of arguments when the deleteHabit command is typed.
 */
public class DeleteHabitCommandParser implements Parser<DeleteHabitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteHabitCommand
     * and returns an DeleteHabitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    @Override
    public DeleteHabitCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteHabitCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteHabitCommand.MESSAGE_USAGE, pe));
        }
    }
}
