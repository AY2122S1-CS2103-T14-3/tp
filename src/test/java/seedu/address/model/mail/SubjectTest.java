package seedu.address.model.mail;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidSubject_throwsIllegalArgumentException() {
        String invalidSubject = "";
        assertThrows(IllegalArgumentException.class, () -> new Subject(invalidSubject));
    }

    @Test
    public void isValidSubject() {
        // null subject
        assertThrows(NullPointerException.class, () -> Subject.isValidSubject(null));

        // invalid subject
        assertFalse(Subject.isValidSubject("")); // empty string
        assertFalse(Subject.isValidSubject(" ")); // spaces only
        assertFalse(Subject.isValidSubject("^")); // only non-alphanumeric characters
        assertFalse(Subject.isValidSubject("subject*")); // contains non-alphanumeric characters

        // valid subject
        assertTrue(Subject.isValidSubject("appropriate subject")); // alphabets only
        assertTrue(Subject.isValidSubject("12345")); // numbers only
        assertTrue(Subject.isValidSubject("appropriate subject 2nd")); // alphanumeric characters
        assertTrue(Subject.isValidSubject("Appropriate Subject")); // with capital letters
        assertTrue(Subject.isValidSubject("This Subject Is Appropriately Long Perhaps 22")); // long subject
    }
}
