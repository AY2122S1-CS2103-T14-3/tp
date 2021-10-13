package seedu.address.ui.template;

import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.mail.Mail;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of mails.
 */
public class MailListPanel extends UiPart<Region> {
    private static final String FXML = "MailListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MailListPanel.class);

    @FXML
    private ListView<Mail> mailListView;

    /**
     * Creates a {@code TemplateListPanel} with the given {@code ObservableList}.
     */
    public MailListPanel(ObservableList<Mail> mailList, HostServices hostServices) {
        super(FXML);
        MailCard.setGetHostController(hostServices);

        mailListView.setItems(mailList);
        mailListView.setCellFactory(listView -> new MailListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Template} using a {@code TemplateCard}.
     */
    class MailListViewCell extends ListCell<Mail> {
        @Override
        protected void updateItem(Mail mail, boolean empty) {
            super.updateItem(mail, empty);

            if (empty || mail == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MailCard(mail).getRoot());
            }
        }
    }

}
