package seedu.address.ui;

import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.contact.ContactListPanel;
import seedu.address.ui.history.HistoryListPanel;
import seedu.address.ui.job.JobListPanel;
import seedu.address.ui.product.ProductListPanel;
import seedu.address.ui.template.MailListPanel;

import seedu.address.ui.template.TemplateListPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ContactListPanel contactListPanel;
    private TemplateListPanel templateListPanel;
    private MailListPanel mailListPanel;
    private ProductListPanel productListPanel;
    private JobListPanel jobListPanel;
    private HistoryListPanel historyListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private StackPane currentPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane templateListPanelPlaceholder;

    @FXML
    private StackPane mailListPanelPlaceholder;

    @FXML
    private StackPane productListPanelPlaceholder;

    @FXML
    private StackPane jobListPanelPlaceholder;

    @FXML
    private StackPane historyListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusBarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts(HostServices hostServices) {
        contactListPanel = new ContactListPanel(logic.getFilteredContactList());
        personListPanelPlaceholder.managedProperty().bind(personListPanelPlaceholder.visibleProperty());
        personListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());

        templateListPanel = new TemplateListPanel(logic.getFilteredTemplateList());
        templateListPanelPlaceholder.managedProperty().bind(templateListPanelPlaceholder.visibleProperty());
        templateListPanelPlaceholder.getChildren().add(templateListPanel.getRoot());

        mailListPanel = new MailListPanel(logic.getFilteredMailList(), hostServices);
        mailListPanelPlaceholder.managedProperty().bind(mailListPanelPlaceholder.visibleProperty());
        mailListPanelPlaceholder.getChildren().add(mailListPanel.getRoot());

        jobListPanel = new JobListPanel(logic.getFilteredJobList());
        jobListPanelPlaceholder.managedProperty().bind(jobListPanelPlaceholder.visibleProperty());
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

        productListPanel = new ProductListPanel(logic.getFilteredProductList());
        productListPanelPlaceholder.managedProperty().bind(productListPanelPlaceholder.visibleProperty());
        productListPanelPlaceholder.getChildren().add(productListPanel.getRoot());

        historyListPanel = new HistoryListPanel(logic.getFilteredHistoryList());
        historyListPanelPlaceholder.managedProperty().bind(historyListPanelPlaceholder.visibleProperty());
        historyListPanelPlaceholder.getChildren().add(historyListPanel.getRoot());

        // Show contacts as initial template
        personListPanelPlaceholder.setVisible(true);
        // Hides initial template
        templateListPanelPlaceholder.setVisible(false);
        // Hides initial template
        mailListPanelPlaceholder.setVisible(false);
        // Hides initial product list
        productListPanelPlaceholder.setVisible(false);
        // Hides initial job list
        jobListPanelPlaceholder.setVisible(false);
        // Hides initial history list
        historyListPanelPlaceholder.setVisible(false);

        // Set current panel to person list
        currentPanel = personListPanelPlaceholder;

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusBarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public ContactListPanel getContactListPanel() {
        return contactListPanel;
    }

    public TemplateListPanel getTemplateListPanel() {
        return templateListPanel;
    }

    public JobListPanel getJobListPanel() {
        return jobListPanel;
    }

    public HistoryListPanel getHistoryListPanel() {
        return historyListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            switch (commandResult.getCommandType()) {
            case CONTACTS:
                showPanel(personListPanelPlaceholder);
                break;

            case TEMPLATE:
                showPanel(templateListPanelPlaceholder);
                break;

            case MAIL:
                showPanel(mailListPanelPlaceholder);
                break;

            case JOBS:
                showPanel(jobListPanelPlaceholder);
                break;

            case PRODUCTS:
                showPanel(productListPanelPlaceholder);
                break;

            case HISTORY:
                showPanel(historyListPanelPlaceholder);
                break;

            case HELP:
                handleHelp();
                break;

            case EXIT:
                handleExit();
                break;

            case COMMON:
                // do nothing
                break;

            default:
                assert false;
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void showPanel(StackPane toShow) {
        if (currentPanel == toShow) {
            return;
        }
        toShow.setVisible(true);
        currentPanel.setVisible(false);
        currentPanel = toShow;
    }
}
