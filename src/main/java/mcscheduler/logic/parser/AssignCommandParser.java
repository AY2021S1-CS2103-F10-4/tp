package mcscheduler.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.stream.Stream;

import mcscheduler.commons.core.Messages;
import mcscheduler.commons.core.index.Index;
import mcscheduler.commons.exceptions.IllegalValueException;
import mcscheduler.logic.commands.AssignCommand;
import mcscheduler.logic.parser.exceptions.ParseException;
import mcscheduler.model.assignment.WorkerRolePair;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AssignCommandParser implements Parser<AssignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignCommand
     * and returns an AssignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_SHIFT, CliSyntax.PREFIX_WORKER_ROLE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_SHIFT, CliSyntax.PREFIX_WORKER_ROLE)) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        Index shiftIndex;
        //Index workerIndex;
        Set<WorkerRolePair> workerRolePairs;
        try {
            shiftIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_SHIFT).get());
            //workerIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_WORKER).get());
            workerRolePairs = ParserUtil.parseWorkerRoles(argMultimap.getAllValues(CliSyntax.PREFIX_WORKER_ROLE));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignCommand.MESSAGE_USAGE), ive);
        }

        //Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());

        return new AssignCommand(shiftIndex, workerRolePairs);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}