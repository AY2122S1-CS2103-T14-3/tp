package seedu.address.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEMPLATES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTemplateCommand extends Command {

    public static final String COMMAND_WORD = "listTemplate";

    public static final String MESSAGE_SUCCESS = "Listed all templates";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTemplateList(PREDICATE_SHOW_ALL_TEMPLATES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
