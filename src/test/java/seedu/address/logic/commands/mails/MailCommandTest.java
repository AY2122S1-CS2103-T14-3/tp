package seedu.address.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TEMPLATE;
import static seedu.address.testutil.TypicalMails.COMPLETED_JOB;
import static seedu.address.testutil.TypicalMails.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTemplates.COMPLETED;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.MyCrm;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Product;
import seedu.address.testutil.MailBuilder;


class MailCommandTest {

    @Test
    public void constructor_nullMail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MailCommand(null, null));
    }

    @Test
    public void execute_mailAcceptedByModelManager_addSuccessful() throws Exception {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Job jobToMail = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Template templateToMail = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        Mail validMail = new Mail(jobToMail, templateToMail);

        MailCommand expectedCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);

        String expectedMessage = String.format(MailCommand.MESSAGE_MAIL_SUCCESS,
                INDEX_FIRST_TEMPLATE.getZeroBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMail(validMail);

        assertCommandSuccess(expectedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_mailAcceptedByModelStub_addSuccessful() throws Exception {
        MailCommandTest.ModelStubAcceptingMailAdded modelStub =
                new MailCommandTest.ModelStubAcceptingMailAdded();
        modelStub.addJob(COMPLETED_JOB);
        modelStub.addTemplate(COMPLETED);

        Mail validMail = new MailBuilder().withJob(COMPLETED_JOB).withTemplate(COMPLETED).build();

        CommandResult commandResult = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE).execute(modelStub);

        assertEquals(String.format(MailCommand.MESSAGE_MAIL_SUCCESS, INDEX_FIRST_TEMPLATE.getZeroBased()),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validMail), modelStub.mailsAdded);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Index outOfBoundJobIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        Index outOfBoundTemplateIndex = Index.fromOneBased(model.getFilteredTemplateList().size() + 1);

        MailCommand mailJobCommand = new MailCommand(outOfBoundJobIndex, INDEX_FIRST_TEMPLATE);
        MailCommand mailTemplateCommand = new MailCommand(INDEX_FIRST_JOB, outOfBoundTemplateIndex);

        assertCommandFailure(mailJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        assertCommandFailure(mailTemplateCommand, model, Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MailCommand completedCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);
        MailCommand doneCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_SECOND_TEMPLATE);

        // same object -> returns true
        assertEquals(completedCommand, completedCommand);

        // same values -> returns true
        MailCommand completedCommandCopy = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);
        assertEquals(completedCommand, completedCommandCopy);

        // different types -> returns false
        assertNotEquals(1, completedCommand);

        // null -> returns false
        assertNotEquals(null, completedCommand);

        // different mail -> returns false
        assertNotEquals(completedCommand, doneCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTemplate(Template template) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMail(Mail mail) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addJob(Job job) {

        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTemplate(Template contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasJob(Job toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTemplate(Template target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteJob(Job target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMail(Mail target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProduct(Product target, Product edited) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Template> getFilteredTemplateList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Mail> getFilteredMailList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTemplateList(Predicate<Template> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMailList(Predicate<Mail> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredJobList(Predicate<Job> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the template, job and only 1 mail being added.
     */
    private static class ModelStubAcceptingMailAdded extends MailCommandTest.ModelStub {
        final ArrayList<Mail> mailsAdded = new ArrayList<>();
        final ArrayList<Template> templatesAdded = new ArrayList<>();
        final ArrayList<Job> jobsAdded = new ArrayList<>();

        @Override
        public void addMail(Mail mail) {
            requireNonNull(mail);
            mailsAdded.add(mail);
        }

        @Override
        public void addTemplate(Template template) {
            requireNonNull(template);
            templatesAdded.add(template);
        }

        @Override
        public void addJob(Job job) {
            requireNonNull(job);
            jobsAdded.add(job);
        }

        @Override
        public ObservableList<Template> getFilteredTemplateList() {
            return FXCollections.observableList(templatesAdded);
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            return FXCollections.observableList(jobsAdded);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new MyCrm();
        }
    }
}
