package seedu.address.model.mail;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BodyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Body(null));
    }

    @Test
    public void constructor_invalidBody_throwsIllegalArgumentException() {
        String invalidBody = "";
        assertThrows(IllegalArgumentException.class, () -> new Body(invalidBody));
    }

    @Test
    public void isValidBody() {
        // null body
        assertThrows(NullPointerException.class, () -> Body.isValidBody(null));

        // invalid body
        assertFalse(Body.isValidBody("")); // empty string
        assertFalse(Body.isValidBody(" ")); // spaces only

        // valid body
        assertTrue(Body.isValidBody("^")); // only non-alphanumeric characters
        assertTrue(Body.isValidBody("body*")); // contains non-alphanumeric characters
        assertTrue(Body.isValidBody("appropriate body")); // alphabets only
        assertTrue(Body.isValidBody("12345")); // numbers only
        assertTrue(Body.isValidBody("appropriate body 2nd")); // alphanumeric characters
        assertTrue(Body.isValidBody("Appropriate Body")); // with capital letters
        assertTrue(Body.isValidBody("This Body Is Appropriately Long Perhaps: 2")); // long body
    }
}
