package seedu.address.ui.job;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.contact.Contact;
import seedu.address.model.job.Job;
import seedu.address.model.products.Product;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Job}.
 */
public class JobCard extends UiPart<Region> {

    private static final String FXML = "JobListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Job job;

    @FXML
    private HBox cardPane;

    // Job Info Fields
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label deliveryDate;
    @FXML
    private FlowPane status;

    // Client Info Fields
    @FXML
    private Label clientInfoHeader;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    // Product Info Fields
    @FXML
    private Label productInfoHeader;
    @FXML
    private Label productName;
    @FXML
    private Label productType;
    @FXML
    private Label productManufacturer;
    @FXML
    private Label productDescription;


    /**
     * Creates a {@code JobCard} with the given {@code Job} and index to display.
     */
    public JobCard(Job job, int displayedIndex) {
        super(FXML);
        this.job = job;

        setJobInfo(job, displayedIndex);
        setClientInfo(job.getClient());
        setProductInfo(job.getProduct());
    }

    private void setJobInfo(Job job, int displayedIndex) {
        id.setText(displayedIndex + ". ");

        String titleText = job.getJobDescription().value;
        if (job.getClient() != null) {
            titleText = titleText + " - " + job.getClient().getName().fullName;
        }
        title.setText(titleText);

        if (job.getDeliveryDate() != null) {
            deliveryDate.setText("Expected Delivery: " + job.getDeliveryDate().toString());
        }

        if (job.isCompleted()) {
            status.getChildren().add(new Label("Complete"));
        } else {
            status.getChildren().add(new Label("In Progress"));
        }
    }

    private void setClientInfo(Contact client) {
        if (client != null) {
            clientInfoHeader.setText("Client Info: ");
            phone.setText("Phone: " + client.getPhone().value);
            address.setText("Address: " + client.getAddress().value);
            email.setText("Email: " + client.getEmail().value);
            client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }
    }

    private void setProductInfo(Product product) {
        if (product != null) {
            productInfoHeader.setText("Product: " + product.getName());
            productType.setText("Type: " + product.getType().toString());
            productManufacturer.setText("Manufacturer: " + product.getManufacturer().toString());
            productDescription.setText("Description: " + product.getDescription().toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JobCard)) {
            return false;
        }

        // state check
        JobCard card = (JobCard) other;
        return id.getText().equals(card.id.getText())
            && job.equals(card.job);
    }
}
