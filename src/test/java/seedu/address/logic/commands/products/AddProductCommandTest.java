package seedu.address.logic.commands.products;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProducts.INTEL_CPU;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
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
import seedu.address.testutil.ProductBuilder;

public class AddProductCommandTest {

    @Test
    public void constructor_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProductCommand(null));
    }

    @Test
    public void execute_productAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingProductAdded modelStub = new ModelStubAcceptingProductAdded();

        CommandResult commandResult = new AddProductCommand(INTEL_CPU).execute(modelStub);

        assertEquals(String.format(AddProductCommand.MESSAGE_SUCCESS, INTEL_CPU), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(INTEL_CPU), modelStub.productsAdded);
    }

    @Test
    public void execute_duplicateProduct_throwsCommandException() {
        AddProductCommand addCommand = new AddProductCommand(INTEL_CPU);
        ModelStub modelStub = new ModelStubWithProduct(INTEL_CPU);

        assertThrows(CommandException.class, AddProductCommand.MESSAGE_DUPLICATE_PRODUCT, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        final AddProductCommand standardCommand = new AddProductCommand(
                new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE).build());

        // same values -> returns true
        AddProductCommand cmdWithSameValues = new AddProductCommand(
                new ProductBuilder(ProductBuilder.DefaultProductIndex.ONE).build());
        assertTrue(standardCommand.equals(cmdWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different product -> returns false
        AddProductCommand cmdWithDiffValues = new AddProductCommand(
                new ProductBuilder(ProductBuilder.DefaultProductIndex.TWO).build());
        assertFalse(standardCommand.equals(cmdWithDiffValues));
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
     * A Model stub that contains a single product.
     */
    private class ModelStubWithProduct extends ModelStub {
        private final Product product;

        ModelStubWithProduct(Product product) {
            requireNonNull(product);
            this.product = product;
        }

        @Override
        public boolean hasProduct(Product product) {
            requireNonNull(product);
            return this.product.isSameProduct(product);
        }
    }

    /**
     * A Model stub that always accept the product being added.
     */
    private class ModelStubAcceptingProductAdded extends ModelStub {
        final ArrayList<Product> productsAdded = new ArrayList<>();

        @Override
        public boolean hasProduct(Product product) {
            requireNonNull(product);
            return productsAdded.stream().anyMatch(product::isSameProduct);
        }

        @Override
        public void addProduct(Product product) {
            requireNonNull(product);
            productsAdded.add(product);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new MyCrm();
        }
    }
}
