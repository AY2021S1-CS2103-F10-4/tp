package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.WorkerDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new WorkerDeleteCommand object
 */
public class DeleteCommandParser implements Parser<WorkerDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the WorkerDeleteCommand
     * and returns a WorkerDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public WorkerDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new WorkerDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, WorkerDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
