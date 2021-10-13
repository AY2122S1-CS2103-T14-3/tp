package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.contacts.DeleteContactCommand;
import seedu.address.logic.commands.contacts.EditContactCommand;
import seedu.address.logic.commands.contacts.FindContactCommand;
import seedu.address.logic.commands.contacts.ListContactCommand;
import seedu.address.logic.commands.history.HistoryCommand;
import seedu.address.logic.commands.jobs.AddJobCommand;
import seedu.address.logic.commands.jobs.DeleteJobCommand;
import seedu.address.logic.commands.jobs.ListJobCommand;
import seedu.address.logic.commands.mails.AddTemplateCommand;
import seedu.address.logic.commands.mails.DeleteTemplateCommand;
import seedu.address.logic.commands.mails.ListTemplateCommand;
import seedu.address.logic.commands.mails.MailCommand;
import seedu.address.logic.commands.products.AddProductCommand;
import seedu.address.logic.commands.products.DeleteProductCommand;
import seedu.address.logic.commands.products.EditProductCommand;
import seedu.address.logic.commands.products.ListProductCommand;
import seedu.address.logic.parser.contacts.AddContactCommandParser;
import seedu.address.logic.parser.contacts.DeleteContactCommandParser;
import seedu.address.logic.parser.contacts.EditContactCommandParser;
import seedu.address.logic.parser.contacts.FindContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.jobs.AddJobCommandParser;
import seedu.address.logic.parser.jobs.DeleteJobCommandParser;
import seedu.address.logic.parser.mails.AddTemplateCommandParser;
import seedu.address.logic.parser.mails.DeleteTemplateCommandParser;
import seedu.address.logic.parser.mails.MailCommandParser;
import seedu.address.logic.parser.products.AddProductCommandParser;
import seedu.address.logic.parser.products.DeleteProductCommandParser;
import seedu.address.logic.parser.products.EditProductCommandParser;

/**
 * Parses user input.
 */
public class MyCrmParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindContactCommand.COMMAND_WORD:
            return new FindContactCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddProductCommand.COMMAND_WORD:
            return new AddProductCommandParser().parse(arguments);

        case ListProductCommand.COMMAND_WORD:
            return new ListProductCommand();

        case EditProductCommand.COMMAND_WORD:
            return new EditProductCommandParser().parse(arguments);

        case DeleteProductCommand.COMMAND_WORD:
            return new DeleteProductCommandParser().parse(arguments);

        case AddTemplateCommand.COMMAND_WORD:
            return new AddTemplateCommandParser().parse(arguments);

        case ListTemplateCommand.COMMAND_WORD:
            return new ListTemplateCommand();

        case DeleteTemplateCommand.COMMAND_WORD:
            return new DeleteTemplateCommandParser().parse(arguments);

        case MailCommand.COMMAND_WORD:
            return new MailCommandParser().parse(arguments);

        case AddJobCommand.COMMAND_WORD:
            return new AddJobCommandParser().parse(arguments);

        case ListJobCommand.COMMAND_WORD:
            return new ListJobCommand();

        case DeleteJobCommand.COMMAND_WORD:
            return new DeleteJobCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
