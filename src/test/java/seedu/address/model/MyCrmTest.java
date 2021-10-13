package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProducts.INTEL_CPU;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.job.Job;
import seedu.address.model.mail.Mail;
import seedu.address.model.mail.Template;
import seedu.address.model.products.Product;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.ProductBuilder;

public class MyCrmTest {

    private final MyCrm addressBook = new MyCrm();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        MyCrm newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicateContactException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    //// Product tests

    @Test
    public void hasProduct_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasProduct(null));
    }

    @Test
    public void hasProduct_productNotInMyCrm_returnsFalse() {
        assertFalse(addressBook.hasProduct(INTEL_CPU));
    }

    @Test
    public void hasProduct_productInMyCrm_returnsTrue() {
        addressBook.addProduct(INTEL_CPU);
        assertTrue(addressBook.hasProduct(INTEL_CPU));
    }

    @Test
    public void hasProduct_productWithSameIdentityFieldsInMyCrm_returnsTrue() {
        addressBook.addProduct(INTEL_CPU);
        Product editedProduct = new ProductBuilder(INTEL_CPU).withManufacturer("Asus").build();
        assertTrue(addressBook.hasProduct(editedProduct));
    }


    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getProductList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getProductList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Contact> persons = FXCollections.observableArrayList();
        private final ObservableList<Template> templates = FXCollections.observableArrayList();
        private final ObservableList<Product> products = FXCollections.observableArrayList();
        private final ObservableList<Job> jobs = FXCollections.observableArrayList();
        private final ObservableList<Mail> mails = FXCollections.observableArrayList();

        AddressBookStub(Collection<Contact> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Contact> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Template> getTemplateList() {
            return templates;
        }

        @Override
        public ObservableList<Product> getProductList() {
            return products;
        }

        @Override
        public ObservableList<Job> getJobList() {
            return jobs;
        }

        @Override
        public ObservableList<Mail> getMailList() {
            return mails;
        }
    }

}
