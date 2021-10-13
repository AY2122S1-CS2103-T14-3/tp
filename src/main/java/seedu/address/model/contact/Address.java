package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Represents a Contact's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address implements ContactComponent<Address> {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private static final Address EMPTY_ADDRESS = new Address();
    public final String value;

    /**
     * Constructs an {@code Address}.
     */
    public Address() {
        value = null;
    }

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    public static Address getAddress(String address) throws ParseException {
        requireNonNull(address);

        if (address.length() == 0) {
            return EMPTY_ADDRESS;
        } else {
            return ParserUtil.parseAddress(address);
        }
    }

    public static Address getPhone(Optional<String> address) throws ParseException {
        requireNonNull(address);

        if (address.orElse("").length() == 0) {
            return EMPTY_ADDRESS;
        } else {
            return ParserUtil.parseAddress(address.get());
        }
    }


    public static Address getEmptyAddress() {
        return EMPTY_ADDRESS;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_ADDRESS;
    }

    @Override
    public String orElse(String alternativeString) {
        return this.isEmpty() ? alternativeString : this.value;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
