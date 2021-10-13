package seedu.address.model.contact;

/**
 * Contact components are essentially boxes of strings.
 */
public interface ContactComponent<T> {
    boolean isEmpty();

    String orElse(String alternativeString);
}
