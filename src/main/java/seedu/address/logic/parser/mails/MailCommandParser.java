package seedu.address.logic.parser.mails;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMPLATE_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.mails.MailCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTemplateCommand object
 */
public class MailCommandParser implements Parser<MailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTemplateCommand
     * and returns a DeleteTemplateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_INDEX, PREFIX_TEMPLATE_INDEX);

        if (ArgumentTokenizer.arePrefixesNotPresent(argMultimap, PREFIX_JOB_INDEX, PREFIX_TEMPLATE_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailCommand.MESSAGE_USAGE));
        }

        Index jobIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_JOB_INDEX).get());
        Index templateIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEMPLATE_INDEX).get());

        return new MailCommand(jobIndex, templateIndex);
    }
}
