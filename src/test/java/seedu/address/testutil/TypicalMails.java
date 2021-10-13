package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;

import seedu.address.model.MyCrm;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;

public class TypicalMails {

    public static final Template COMPLETED_TEMPLATE = new TemplateBuilder().withSubject(VALID_SUBJECT_COMPLETE)
            .withBody("Dear valued customer, your order has been completed and ready for collection").build();

    public static final Job COMPLETED_JOB = new JobBuilder().withCompletionStatus(true).build();

    public static final Mail COMPLETED_MAIL = new MailBuilder().build();

    private TypicalMails() {}

    /**
     * Returns an {@code AddressBook} with all the typical templates.
     */
    public static MyCrm getTypicalAddressBook() {
        MyCrm ab = new MyCrm();
        ab.addJob(COMPLETED_JOB);
        ab.addTemplate(COMPLETED_TEMPLATE);
        return ab;
    }

}
