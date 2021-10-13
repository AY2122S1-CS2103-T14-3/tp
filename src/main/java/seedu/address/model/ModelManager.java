package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;
import seedu.address.model.history.History;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Product;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MyCrm addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredPersons;
    private final FilteredList<Template> filteredTemplates;
    private final FilteredList<Mail> filteredMails;
    private final FilteredList<Job> filteredJobs;
    private final FilteredList<Product> filteredProducts;
    private final FilteredList<History> filteredHistories;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new MyCrm(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTemplates = new FilteredList<>(this.addressBook.getTemplateList());
        filteredMails = new FilteredList<>(this.addressBook.getMailList());
        filteredJobs = new FilteredList<>(this.addressBook.getJobList());
        filteredProducts = new FilteredList<>(this.addressBook.getProductList());
        filteredHistories = new FilteredList<>(this.addressBook.getHistoryList());
    }

    public ModelManager() {
        this(new MyCrm(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasContact(Contact person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasTemplate(Template template) {
        requireNonNull(template);
        return addressBook.hasTemplate(template);
    }

    @Override
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return addressBook.hasJob(job);
    }

    @Override
    public boolean hasHistory(History history) {
        requireNonNull(history);
        return addressBook.hasHistory(history);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteJob(Job target) {
        addressBook.removeJob(target);
    }

    @Override
    public void addContact(Contact person) {
        addressBook.addPerson(person);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void addTemplate(Template template) {
        addressBook.addTemplate(template);
        updateFilteredTemplateList(PREDICATE_SHOW_ALL_TEMPLATES);
    }

    @Override
    public void deleteTemplate(Template target) {
        addressBook.removeTemplate(target);
    }

    @Override
    public void addMail(Mail mail) {
        addressBook.addMail(mail);
        updateFilteredMailList(PREDICATE_SHOW_ALL_MAILS);
    }

    @Override
    public void deleteMail(Mail target) {
        addressBook.removeMail(target);
    }

    @Override
    public void addJob(Job job) {
        addressBook.addJob(job);
        updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
    }

    @Override
    public void addHistory(History history) {
        addressBook.addHistory(history);
        updateFilteredHistoryList(PREDICATE_SHOW_ALL_HISTORIES);
    }

    @Override
    public void setContact(Contact target, Contact editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Products ================================================================================

    @Override
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return addressBook.hasProduct(product);
    }

    @Override
    public void addProduct(Product product) {
        requireNonNull(product);
        addressBook.addProduct(product);
        updateFilteredProductList(PREDICATE_SHOW_ALL_PRODUCTS);
    }

    @Override
    public void deleteProduct(Product product) {
        requireNonNull(product);
        addressBook.removeProduct(product);
    }

    @Override
    public void setProduct(Product target, Product editedProduct) {
        requireAllNonNull(target, editedProduct);
        addressBook.setProduct(target, editedProduct);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Template} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Template> getFilteredTemplateList() {
        return filteredTemplates;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Mail} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Mail> getFilteredMailList() {
        return filteredMails;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Product} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Product> getFilteredProductList() {
        return filteredProducts;
    }

    @Override
    public ObservableList<Job> getFilteredJobList() {
        return filteredJobs;
    }

    @Override
    public ObservableList<History> getFilteredHistoryList() {
        return filteredHistories;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTemplateList(Predicate<Template> predicate) {
        requireNonNull(predicate);
        filteredTemplates.setPredicate(predicate);
    }

    @Override
    public void updateFilteredMailList(Predicate<Mail> predicate) {
        requireNonNull(predicate);
        filteredMails.setPredicate(predicate);
    }

    @Override
    public void updateFilteredProductList(Predicate<Product> predicate) {
        requireNonNull(predicate);
        filteredProducts.setPredicate(predicate);
    }

    @Override
    public void updateFilteredJobList(Predicate<Job> predicate) {
        requireNonNull(predicate);
        filteredJobs.setPredicate(predicate);
    }

    @Override
    public void updateFilteredHistoryList(Predicate<History> predicate) {
        requireNonNull(predicate);
        filteredHistories.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
