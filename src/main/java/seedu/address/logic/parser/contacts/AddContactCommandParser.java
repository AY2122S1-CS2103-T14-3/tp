package seedu.address.logic.parser.contacts;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.tag.Tag;

public class AddContactCommandParser implements Parser<AddContactCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddContactCommand}
     * and returns a {@code AddContactCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddContactCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_USAGE));
        }

        Optional<String> nameWrapper = argMultimap.getValue(PREFIX_NAME);
        Name name;
        if (nameWrapper.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_USAGE));
        } else if (nameWrapper.get().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_USAGE));
        } else {
            name = Name.getName(nameWrapper.get());
        }

        Optional<String> phoneWrapper = argMultimap.getValue(PREFIX_PHONE);
        Phone phone;
        phone = phoneWrapper.orElse("").equals("")
                ? Phone.getEmptyPhone()
                : Phone.getPhone(phoneWrapper.get());

        Optional<String> emailWrapper = argMultimap.getValue(PREFIX_EMAIL);
        Email email = emailWrapper.orElse("").equals("")
                ? Email.getEmptyEmail()
                : Email.getEmail(emailWrapper.get());


        Optional<String> addressWrapper = argMultimap.getValue(PREFIX_ADDRESS);
        Address address = addressWrapper.orElse("").equals("")
                ? Address.getEmptyAddress()
                : Address.getAddress(addressWrapper.get());

        if (phoneWrapper.isEmpty() && emailWrapper.isEmpty() && addressWrapper.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactCommand.MESSAGE_AT_LEAST_ONE_COMPONENT));
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Contact contact = new Contact(name, phone, email, address, tagList);

        return new AddContactCommand(contact);
    }
}
