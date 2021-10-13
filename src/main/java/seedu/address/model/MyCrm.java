package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;
import seedu.address.model.history.History;
import seedu.address.model.history.UniqueHistoryList;
import seedu.address.model.job.Job;
import seedu.address.model.job.UniqueJobList;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.mail.UniqueMailList;
import seedu.address.model.mail.UniqueTemplateList;
import seedu.address.model.products.Product;
import seedu.address.model.products.UniqueProductList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class MyCrm implements ReadOnlyAddressBook {

    private final UniqueContactList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueContactList();
    }

    private final UniqueTemplateList templates;
    {
        templates = new UniqueTemplateList();
    }

    private final UniqueProductList products;
    {
        products = new UniqueProductList();
    }

    private final UniqueJobList jobs;
    {
        jobs = new UniqueJobList();
    }

    private final UniqueHistoryList histories;
    {
        histories = new UniqueHistoryList();
    }
    private final UniqueMailList mails;
    {
        mails = new UniqueMailList();
    }

    public MyCrm() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public MyCrm(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Contact> persons) {
        this.persons.setContacts(persons);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTemplates(List<Template> templates) {
        this.templates.setTemplates(templates);
    }

    /**
     * Replaces the contents of the product list with {@code products}.
     * {@code products} must not contain duplicate products.
     */
    public void setProducts(List<Product> products) {
        this.products.setProducts(products);
    }

    /**
     * Replaces the contents of the jobs list with {@code jobs}.
     * {@code jobs} must not contain duplicate jobs.
     */
    public void setJobs(List<Job> jobs) {
        this.jobs.setJobs(jobs);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTemplates(newData.getTemplateList());
        setProducts(newData.getProductList());
        setJobs(newData.getJobList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Contact person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a template with the same identity as {@code template} exists in the address book.
     */
    public boolean hasTemplate(Template template) {
        requireNonNull(template);
        return templates.contains(template);
    }

    /**
     * Returns true if a job with the same identity as {@code job} exists in the address book.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Contact p) {
        persons.add(p);
    }

    /**
     * Adds a template to the address book.
     * The template must not already exist in the address book.
     */
    public void addTemplate(Template t) {
        templates.add(t);
    }

    /**
     * Adds a mail to the address book.
     */
    public void addMail(Mail m) {
        mails.add(m);
    }

    /**
     * Adds a job to the address book.
     * The job must not already exist in the address book.
     */
    public void addJob(Job j) {
        jobs.add(j);
    }

    /**
     * Adds a entered command to the address book.
     * The entered command must not already exist in MyCRM.
     */
    public void addHistory(History history) {
        histories.add(history);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Contact target, Contact editedPerson) {
        requireNonNull(editedPerson);

        persons.setContact(target, editedPerson);
    }

    /**
     * Replaces the given template {@code target} in the list with {@code editedTemplate}.
     * {@code target} must exist in the address book.
     * The template identity of {@code editedTemplate} must not be the same as another existing template in the
     * address book.
     */
    public void setTemplate(Template target, Template editedTemplate) {
        requireNonNull(editedTemplate);

        templates.setTemplate(target, editedTemplate);
    }

    /**
     * Replaces the given job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the address book.
     * The job identity of {@code editedJob} must not be the same as another existing job in the
     * address book.
     */
    public void setJob(Job target, Job editedJob) {
        requireNonNull(editedJob);
        jobs.setJob(target, editedJob);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Contact key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTemplate(Template key) {
        templates.remove(key);
    }

    /**
     * Adds a mail to the address book.
     */
    public void removeMail(Mail key) {
        mails.remove(key);
    }

    //// Product Methods

    /**
     * Adds a product to the address book.
     * The product must not already exist in MyCRM.
     */
    public void addProduct(Product p) {
        products.add(p);
    }

    /**
     * Returns true if a product with the same identity as {@code product} exists in MyCRM.
     */
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return products.contains(product);
    }

    /**
     * Removes {@code key} from this {@code MyCRM}.
     * {@code key} must exist in MyCRM.
     */
    public void removeProduct(Product key) {
        products.remove(key);
    }

    /**
     * Replaces the given product {@code target} in the list with {@code editedProduct}.
     * {@code target} must exist in MyCrm.
     * The product identity of {@code editedProduct} must not be the same as another existing product
     * in MyCrm.
     */
    public void setProduct(Product target, Product editedProduct) {
        requireNonNull(editedProduct);
        products.setProduct(target, editedProduct);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeJob(Job key) {
        jobs.remove(key);
    }

    /**
     * Returns true if a entered command with the same identity as {@code history} exists in MyCRM.
     */
    public boolean hasHistory(History history) {
        requireNonNull(history);
        return histories.contains(history);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Contact> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Template> getTemplateList() {
        return templates.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Product> getProductList() {
        return products.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<History> getHistoryList() {
        return histories.asUnmodifiableObservableList();
}
    public ObservableList<Mail> getMailList() {
        return mails.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MyCrm // instanceof handles nulls
                && persons.equals(((MyCrm) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
