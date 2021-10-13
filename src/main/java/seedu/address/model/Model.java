package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.Contact;
import seedu.address.model.history.History;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Product;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;
    Predicate<Template> PREDICATE_SHOW_ALL_TEMPLATES = unused -> true;
    Predicate<Product> PREDICATE_SHOW_ALL_PRODUCTS = unused -> true;
    Predicate<Job> PREDICATE_SHOW_ALL_JOBS = unused -> true;
    Predicate<History> PREDICATE_SHOW_ALL_HISTORIES = unused -> true;
    Predicate<Mail> PREDICATE_SHOW_ALL_MAILS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    boolean hasContact(Contact contact);

    /**
     * Returns true if a template with the same identity as {@code template} exists in the address book.
     */
    boolean hasTemplate(Template toAdd);

    /**
     * Returns true if a job with the same identity as {@code job} exists in the address book.
     */
    boolean hasJob(Job toAdd);

    /**
     * Deletes the given contact.
     * The contact must exist in the address book.
     */
    void deleteContact(Contact target);

    /**
     * Deletes the given job.
     * The job must exist in the address book.
     */
    void deleteJob(Job target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the address book.
     */
    void addContact(Contact contact);

    /**
     * Adds the given template.
     * {@code template} must not already exist in the address book.
     */
    void addTemplate(Template template);

    /**
     * Deletes the given template.
     * The template must exist in the address book.
     */
    void deleteTemplate(Template target);

    /**
     * Adds the given mail.
     * {@code mail} must not already exist in the address book.
     */
    void addMail(Mail mail);

    /**
     * Deletes the given mail.
     * The mail must exist in the address book.
     */
    void deleteMail(Mail mail);

    /**
     * Adds the given job.
     * {@code job} must not already exist in the address book.
     */
    void addJob(Job job);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not be the same as another existing contact
     * in the address book.
     */
    void setContact(Contact target, Contact editedContact);

    /**
     * Returns true if a product with the same identity as {@code product} exists in MyCrm.
     */
    boolean hasProduct(Product product);

    /**
     * Adds the given product.
     * {@code product} must not already exist in MyCrm.
     */
    void addProduct(Product product);

    /**
     * Deletes the given product.
     * The product must exist in MyCrm.
     */
    void deleteProduct(Product product);

    /**
     * Replaces the given product {@code target} with {@code editedProduct}.
     * {@code target} must exist in MyCrm.
     * The product identity of {@code editedProduct} must not be the same as another existing product
     * in MyCrm.
     */
    void setProduct(Product target, Product editedProduct);

    /**
     * Returns true if a history command with the same identity as {@code history} exists in MyCrm.
     */
    boolean hasHistory(History history);

    /**
     * Adds the entered command.
     * {@code history} must not already exist in MyCrm.
     */
    void addHistory(History history);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered template list */
    ObservableList<Template> getFilteredTemplateList();

    /** Returns an unmodifiable view of the filtered mail list */
    ObservableList<Mail> getFilteredMailList();

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredProductList();

    /** Returns an unmodifiable view of the filtered job list */
    ObservableList<Job> getFilteredJobList();

    /** Returns an unmodifiable view of the filtered history command list */
    ObservableList<History> getFilteredHistoryList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTemplateList(Predicate<Template> predicate);

    /**
     * Updates the filter of the filtered mail list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMailList(Predicate<Mail> predicate);

    /**
     * Updates the filter of the filtered product list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProductList(Predicate<Product> predicate);

    /**
     * Updates the filter of the filtered job list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredJobList(Predicate<Job> predicate);

    /**
     * Updates the filter of the filtered history command list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHistoryList(Predicate<History> predicate);
}
