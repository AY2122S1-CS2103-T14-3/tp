package seedu.address.model.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.BENSON_JOB;
import static seedu.address.testutil.TypicalJobs.INCOMPLETE;
import static seedu.address.testutil.TypicalMails.COMPLETED_MAIL;
import static seedu.address.testutil.TypicalTemplates.DONE;
import static seedu.address.testutil.TypicalTemplates.THANK_YOU;

import org.junit.jupiter.api.Test;

import seedu.address.model.job.Job;
import seedu.address.testutil.JobBuilder;
import seedu.address.testutil.MailBuilder;
import seedu.address.testutil.TemplateBuilder;

public class MailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Job validJob = new JobBuilder().build();
        Template validTemplate = new TemplateBuilder().build();

        assertThrows(NullPointerException.class, () -> new Mail(null, null));
        assertThrows(NullPointerException.class, () -> new Mail(validJob, null));
        assertThrows(NullPointerException.class, () -> new Mail(null, validTemplate));
    }

    @Test
    public void isSameMail() {
        // same object -> returns true
        assertTrue(COMPLETED_MAIL.isSameMail(COMPLETED_MAIL));

        // null -> returns false
        assertFalse(COMPLETED_MAIL.isSameMail(null));

        // same template, same job but different status, all other attributes same -> returns false
        Mail editedCompleted = new MailBuilder(COMPLETED_MAIL).withJob(INCOMPLETE).build();
        assertTrue(COMPLETED_MAIL.isSameMail(editedCompleted));

        // different template, all other attributes same -> returns false
        editedCompleted = new MailBuilder(COMPLETED_MAIL).withJob(BENSON_JOB).build();
        assertFalse(COMPLETED_MAIL.isSameMail(editedCompleted));

        // different template, all other attributes same -> returns false
        editedCompleted = new MailBuilder(COMPLETED_MAIL).withTemplate(DONE).build();
        assertFalse(COMPLETED_MAIL.isSameMail(editedCompleted));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Mail completedCopy = new MailBuilder(COMPLETED_MAIL).build();
        assertEquals(COMPLETED_MAIL, completedCopy);

        // same object -> returns true
        assertEquals(COMPLETED_MAIL, COMPLETED_MAIL);

        // null -> returns false
        assertNotEquals(null, COMPLETED_MAIL);

        // different type -> returns false
        assertNotEquals(5, COMPLETED_MAIL);

        // different job -> returns false
        Mail editedMail = new MailBuilder(COMPLETED_MAIL).withJob(BENSON_JOB).build();
        assertNotEquals(COMPLETED_MAIL, editedMail);

        // different template -> returns false
        editedMail = new MailBuilder(COMPLETED_MAIL).withTemplate(THANK_YOU).build();
        assertNotEquals(COMPLETED_MAIL, editedMail);
    }

    @Test
    public void isSameString() {
        Job job = new JobBuilder().build();
        Template template = new TemplateBuilder().build();
        Mail mail = new MailBuilder().build();
        assertEquals(mail.toString(), String.format("Job: %s; Template: %s", job, template));
    }
}
