package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.history.History;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Product;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getPersonList();

    /**
     * Returns an unmodifiable view of the templates list.
     * This list will not contain any template persons.
     */
    ObservableList<Template> getTemplateList();

    /**
     * Returns an unmodifiable view of the products list.
     * This list will not contain any duplicate products.
     */
    ObservableList<Product> getProductList();

    /**
     * Returns an unmodifiable view of the jobs list.
     * This list will not contain any duplicate jobs.
     */
    ObservableList<Job> getJobList();

    /**
     * Returns an unmodifiable view of the jobs list.
     * This list will not contain any duplicate jobs.
     */

    ObservableList<History> getHistoryList();

    ObservableList<Mail> getMailList();

}
