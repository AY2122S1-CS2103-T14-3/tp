package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;

import seedu.mycrm.logic.commands.contacts.ListContactCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListContactCommand object
 */
public class ListContactCommandParser implements Parser<ListContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListContactCommand
     * and returns an ListContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new ListContactCommand(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (nameKeywords.length > 1 || !nameKeywords[0].equals("-a")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListContactCommand.MESSAGE_USAGE));
        }

        return new ListContactCommand(PREDICATE_SHOW_ALL_CONTACTS);
    }
}