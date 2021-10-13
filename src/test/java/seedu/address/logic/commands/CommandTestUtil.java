package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_NAME;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_NAME;
import static seedu.address.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contacts.EditContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.MyCrm;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.mail.SubjectContainsKeywordsPredicate;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Description;
import seedu.address.model.products.Manufacturer;
import seedu.address.model.products.Product;
import seedu.address.model.products.ProductName;
import seedu.address.model.products.ProductNameContainsKeywordsPredicate;
import seedu.address.model.products.Type;
import seedu.address.testutil.EditContactDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_SUBJECT_DONE = "Order is done";
    public static final String VALID_SUBJECT_COMPLETE = "Your order has been completed";

    public static final String VALID_BODY_DONE = "Your order is done and ready for collection";
    public static final int HEAD_SUBJECT_SIZE = 2;

    public static final EditContactCommand.EditContactDescriptor DESC_AMY;
    public static final EditContactCommand.EditContactDescriptor DESC_BOB;

    public static final ProductName VALID_PRODUCT_NAME = DEFAULT_PRODUCT_ONE_NAME;
    public static final Type VALID_PRODUCT_TYPE = DEFAULT_PRODUCT_ONE_TYPE;
    public static final Manufacturer VALID_PRODUCT_MANUFACTURER = DEFAULT_PRODUCT_ONE_MANUFACTURER;
    public static final Description VALID_PRODUCT_DESCRIPTION = DEFAULT_PRODUCT_ONE_DESCRIPTION;

    public static final String PRODUCT_NAME_DESC = " " + PREFIX_PRODUCT_NAME + VALID_PRODUCT_NAME;
    public static final String PRODUCT_TYPE_DESC = " " + PREFIX_PRODUCT_TYPE + VALID_PRODUCT_TYPE;
    public static final String PRODUCT_MANUFACTURER_DESC = " " + PREFIX_PRODUCT_MANUFACTURER
            + VALID_PRODUCT_MANUFACTURER;
    public static final String PRODUCT_DESCRIPTION_DESC = " " + PREFIX_PRODUCT_DESCRIPTION + VALID_PRODUCT_DESCRIPTION;

    public static final String PRODUCT_TWO_NAME_DESC = " " + PREFIX_PRODUCT_NAME + DEFAULT_PRODUCT_TWO_NAME;
    public static final String PRODUCT_TWO_TYPE_DESC = " " + PREFIX_PRODUCT_TYPE + DEFAULT_PRODUCT_TWO_TYPE;
    public static final String PRODUCT_TWO_MANUFACTURER_DESC = " " + PREFIX_PRODUCT_MANUFACTURER
            + DEFAULT_PRODUCT_TWO_MANUFACTURER;
    public static final String PRODUCT_TWO_DESCRIPTION_DESC = " " + PREFIX_PRODUCT_DESCRIPTION
            + DEFAULT_PRODUCT_TWO_DESCRIPTION;

    public static final String PRODUCT_EMPTY_TYPE_DESC = " " + PREFIX_PRODUCT_TYPE;
    public static final String PRODUCT_EMPTY_MANUFACTURER_DESC = " " + PREFIX_PRODUCT_MANUFACTURER;
    public static final String PRODUCT_EMPTY_DESCRIPTION_DESC = " " + PREFIX_PRODUCT_DESCRIPTION;

    public static final String INVALID_PRODUCT_NAME_DESC = " " + PREFIX_PRODUCT_NAME
            + PRODUCT_TYPE_DESC; // product name is empty

    static {
        DESC_AMY = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, command.getType());
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MyCrm expectedAddressBook = new MyCrm(actualModel.getAddressBook());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredContactList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredContactList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());

        Contact person = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredContactList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the template at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTemplateAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTemplateList().size());

        Template template = model.getFilteredTemplateList().get(targetIndex.getZeroBased());
        final String[] splitSubject = template.getSubject().subject.split("\\s+");
        model.updateFilteredTemplateList(new SubjectContainsKeywordsPredicate(Stream.of(splitSubject)
                .limit(HEAD_SUBJECT_SIZE)
                .collect(Collectors.toList())));

        assertEquals(1, model.getFilteredTemplateList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the product at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showProductAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProductList().size());

        Product product = model.getFilteredProductList().get(targetIndex.getZeroBased());
        final String[] splitName = product.getName().orElse("").split("\\s+");
        model.updateFilteredProductList(new ProductNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProductList().size());
    }
}
