package seedu.address.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;

/**
 * Lists all contacts in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "listContact";

    public static final String MESSAGE_SUCCESS = "Here are the listed all contacts:";

    private static final CommandType COMMAND_TYPE = CommandType.CONTACTS;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
