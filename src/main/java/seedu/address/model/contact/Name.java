package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Represents a Contact's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements ContactComponent<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final Name EMPTY_NAME = new Name();
    public final String fullName;

    /**
     * Constructs a {@code Name}.
     */
    public Name() {
        fullName = null;
    }

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    public static Name getName(String name) throws ParseException {
        requireNonNull(name);
        assert name.length() > 0;

        return ParserUtil.parseName(name);
    }

    public static Name getName(Optional<String> name) throws ParseException {
        requireNonNull(name);
        assert name.orElse("").length() > 0;

        String nameString = name.get();
        return ParserUtil.parseName(nameString);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_NAME;
    }

    @Override
    public String orElse(String alternativeString) {
        return this.isEmpty() ? alternativeString : this.fullName;
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
