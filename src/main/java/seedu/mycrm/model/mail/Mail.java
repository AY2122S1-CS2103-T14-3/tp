package seedu.mycrm.model.mail;

import static java.util.Objects.requireNonNull;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import seedu.mycrm.model.job.Job;

public class Mail {

    private final Job job;
    private final Template template;

    /**
     * Creates a mail with the mail and template.
     *
     */
    public Mail(Job job, Template template) {
        requireNonNull(job);
        requireNonNull(template);
        this.job = job;
        this.template = template;
    }

    public Job getJob() {
        return job;
    }
    public Template getTemplate() {
        return template;
    }

    /**
     * Returns job client email
     */
    public String getMailEmail() {
        return job.getClient().getEmail().value;
    }

    /**
     * Returns template subject header
     */
    public String getMailSubject() {
        return template.getSubject().toString();
    }

    /**
     * Returns template body text
     */
    public String getMailBody() {
        return template.getMailReadyBody();
    }

    /**
     * Returns true if both mails have the same job and template
     * This defines a weaker notion of equality between two mails.
     */
    public boolean isSameMail(Mail otherMail) {
        if (otherMail == this) {
            return true;
        }

        return otherMail != null
                && otherMail.getJob().equals(getJob())
                && otherMail.getTemplate().equals(getTemplate());
    }

    /**
     * Returns true if both mails templates have the same fields.
     * This defines a stronger notion of equality between two mails.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Mail) {
            Mail otherMail = (Mail) other;
            return otherMail.getJob().equals(getJob())
                    && otherMail.getTemplate().equals(getTemplate());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJob(), getTemplate());
    }

    @Override
    public String toString() {
        return String.format("Job: %s; Template: %s", job, template);
    }

    /**
     * Creates a string URL mailto with job and template information.
     *
     * @return mailto URL
     */
    public String constructMail() {
        return String.format("mailto:%s?subject=%s&body=%s",
                job.getClientEmail(),
                urlEncode(template.getSubject().toString()),
                urlEncode(template.getBody().toString()));
    }

    /**
     * Encodes given string into URL friendly string.
     * Replaces ' ' and '\n' with URL variate.
     *
     * @param str target subject/body string
     * @return URL friendly string
     */
    public static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8)
                .replace("+", "%20")
                .replace("%5Cn", "%0D%0A");
    }
}
