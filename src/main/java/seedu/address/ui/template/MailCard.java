package seedu.address.ui.template;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.mail.Mail;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Template}.
 */
public class MailCard extends UiPart<Region> {

    private static final String FXML = "MailListCard.fxml";

    private static HostServices myHostServices;

    public final Mail mail;

    @FXML
    private HBox cardPane;
    @FXML
    private Hyperlink link;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private Label body;

    /**
     * Creates a {@code TemplateCode} with the given {@code Template} and index to display.
     */
    public MailCard(Mail mail) {
        super(FXML);
        this.mail = mail;
        link.setText("Mail Here");
        email.setText(mail.getJob().getClient().getEmail().value);
        subject.setText(mail.getTemplate().getSubject().subject);
        body.setText(mail.getTemplate().getBody().body);

        link.setOnAction(e -> {
            myHostServices.showDocument(mail.constructMail());
        });
    }

    public static void setGetHostController(HostServices hostServices) {
        myHostServices = hostServices;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MailCard)) {
            return false;
        }

        // state check
        MailCard card = (MailCard) other;
        return mail.equals(card.mail);
    }
}
