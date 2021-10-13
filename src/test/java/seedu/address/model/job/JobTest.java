package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContacts.CARL;
import static seedu.address.testutil.TypicalJobs.COMPLETED;
import static seedu.address.testutil.TypicalProducts.SAMSUNG_SSD;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobTest {
    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(COMPLETED.isSameJob(COMPLETED));

        // null -> returns false
        assertFalse(COMPLETED.isSameJob(null));

        // same contact, product and job description with all other attributes different -> returns true
        Job editedCompleted = new JobBuilder(COMPLETED).withDeliveryDate("12/12/2012")
                .withCompletionStatus(false).build();
        assertTrue(COMPLETED.isSameJob(editedCompleted));

        // different contact, all other attributes same -> returns false
        editedCompleted = new JobBuilder(COMPLETED).withClient(CARL).build();
        assertFalse(COMPLETED.isSameJob(editedCompleted));

        // different description, all other attributes same -> returns false
        editedCompleted = new JobBuilder(COMPLETED).withJobDescription("RANDOM JOB").build();
        assertFalse(COMPLETED.isSameJob(editedCompleted));

        // different product, all other attributes same -> returns false
        editedCompleted = new JobBuilder(COMPLETED).withProduct(SAMSUNG_SSD).build();
        assertFalse(COMPLETED.isSameJob(editedCompleted));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(COMPLETED, COMPLETED);

        // null -> returns false
        assertNotEquals(null, COMPLETED);

        // different type -> returns false
        assertNotEquals(5, COMPLETED);
    }
}
