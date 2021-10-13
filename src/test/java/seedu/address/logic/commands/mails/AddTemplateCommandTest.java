package seedu.address.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.MyCrm;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Product;
import seedu.address.testutil.TemplateBuilder;

class AddTemplateCommandTest {
    @Test
    public void constructor_nullTemplate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTemplateCommand(null));
    }

    @Test
    public void execute_templateAcceptedByModel_addSuccessful() throws Exception {
        AddTemplateCommandTest.ModelStubAcceptingTemplateAdded modelStub =
                new ModelStubAcceptingTemplateAdded();
        Template validTemplate = new TemplateBuilder().build();

        CommandResult commandResult = new AddTemplateCommand(validTemplate).execute(modelStub);

        assertEquals(String.format(AddTemplateCommand.MESSAGE_SUCCESS, validTemplate),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validTemplate), modelStub.templatesAdded);
    }

    @Test
    public void execute_duplicateTemplate_throwsCommandException() {
        Template validTemplate = new TemplateBuilder().build();
        AddTemplateCommand addCommand = new AddTemplateCommand(validTemplate);
        AddTemplateCommandTest.ModelStub modelStub = new ModelStubWithTemplate(validTemplate);

        assertThrows(CommandException.class, AddTemplateCommand.MESSAGE_DUPLICATE_TEMPLATE, (
            ) -> addCommand.execute(modelStub));
    }

    @Test
    public void type() {
        Template validTemplate = new TemplateBuilder().build();
        AddTemplateCommand addCommand = new AddTemplateCommand(validTemplate);

        assertEquals(addCommand.getType(), CommandType.TEMPLATE);
        assertNotEquals(addCommand.getType(), CommandType.MAIL);
    }
    @Test
    public void equals() {
        Template alice = new TemplateBuilder().withSubject("To Alice").build();
        Template bob = new TemplateBuilder().withSubject("To Bob").build();
        AddTemplateCommand addAliceCommand = new AddTemplateCommand(alice);
        AddTemplateCommand addBobCommand = new AddTemplateCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddTemplateCommand addAliceCommandCopy = new AddTemplateCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different template -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
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
     * A Model stub that contains a single contact.
     */
    private static class ModelStubWithTemplate extends AddTemplateCommandTest.ModelStub {
        private final Template template;

        ModelStubWithTemplate(Template template) {
            requireNonNull(template);
            this.template = template;
        }

        @Override
        public boolean hasTemplate(Template template) {
            requireNonNull(template);
            return this.template.isSameTemplate(template);
        }
    }

    /**
     * A Model stub that always accept the template being added.
     */
    private static class ModelStubAcceptingTemplateAdded extends AddTemplateCommandTest.ModelStub {
        final ArrayList<Template> templatesAdded = new ArrayList<>();

        @Override
        public boolean hasTemplate(Template template) {
            requireNonNull(template);
            return templatesAdded.stream().anyMatch(template::isSameTemplate);
        }

        @Override
        public void addTemplate(Template template) {
            requireNonNull(template);
            templatesAdded.add(template);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new MyCrm();
        }
    }
}
