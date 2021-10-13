package seedu.address.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.mail.Template;

/**
 * Adds a template to the address book.
 */
public class AddTemplateCommand extends Command {

    public static final String COMMAND_WORD = "addTemplate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a template to the address book. "
            + "Parameters: "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_BODY + "BODY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SUBJECT + "Completed Job "
            + PREFIX_BODY + "Hello, your product is ready! ";

    public static final String MESSAGE_SUCCESS = "New template added: %1$s";
    public static final String MESSAGE_DUPLICATE_TEMPLATE = "This template already exists in the address book";

    private static final CommandType COMMAND_TYPE = CommandType.TEMPLATE;

    private final Template toAdd;

    /**
     * Creates an AddTemplateCommand to add the specified {@code Template}
     */
    public AddTemplateCommand(Template template) {
        requireNonNull(template);
        toAdd = template;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTemplate(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEMPLATE);
        }

        model.addTemplate(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTemplateCommand // instanceof handles nulls
                && toAdd.equals(((AddTemplateCommand) other).toAdd));
    }
}
