package seedu.address.testutil;

import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;

/**
 * A utility class to help with building Mail objects.
 */
public class MailBuilder {
    private Job job;
    private Template template;

    /**
     * Creates a {@code MailBuilder} with the default details.
     */
    public MailBuilder() {
        job = new JobBuilder().build();
        template = new TemplateBuilder().build();
    }

    /**
     * Creates a {@code MailBuilder} with the default details.
     */
    public MailBuilder(Mail mailToCopy) {
        job = mailToCopy.getJob();
        template = mailToCopy.getTemplate();
    }

    /**
     * Sets the {@code Job} of the {@code Mail} that we are building.
     */
    public MailBuilder withJob(Job job) {
        this.job = job;
        return this;
    }

    /**
     * Sets the {@code Template} of the {@code Mail} that we are building.
     */
    public MailBuilder withTemplate(Template template) {
        this.template = template;
        return this;
    }

    public Mail build() {
        return new Mail(job, template);
    }

}
