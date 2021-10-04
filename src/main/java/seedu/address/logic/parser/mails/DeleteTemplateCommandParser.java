package seedu.address.logic.parser.mails;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.mails.DeleteTemplateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTemplateCommand object
 */
public class DeleteTemplateCommandParser implements Parser<DeleteTemplateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTemplateCommand
     * and returns a DeleteTemplateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTemplateCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTemplateCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTemplateCommand.MESSAGE_USAGE), pe);
        }
    }

}
