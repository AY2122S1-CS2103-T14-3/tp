package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.mail.Template;

/**
 * Deletes a template identified using it's displayed index from the address book.
 */
public class DeleteTemplateCommand extends Command {

    public static final String COMMAND_WORD = "deleteTemplate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the template identified by the index number used in the displayed template list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TEMPLATE_SUCCESS = "Deleted Template: %1$s";

    private final Index targetIndex;

    public DeleteTemplateCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Template> lastShownList = model.getFilteredTemplateList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
        }

        Template templateToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTemplate(templateToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TEMPLATE_SUCCESS, templateToDelete), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTemplateCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTemplateCommand) other).targetIndex)); // state check
    }
}
