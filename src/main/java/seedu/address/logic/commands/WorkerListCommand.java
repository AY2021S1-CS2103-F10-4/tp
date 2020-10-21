package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_WORKERS;

import seedu.address.model.Model;

/**
 * Lists all workers in the address book to the user.
 */
public class WorkerListCommand extends Command {

    public static final String COMMAND_WORD = "worker-list";

    public static final String MESSAGE_SUCCESS = "Listed all workers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredWorkerList(PREDICATE_SHOW_ALL_WORKERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}