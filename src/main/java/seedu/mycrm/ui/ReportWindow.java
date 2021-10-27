package seedu.mycrm.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.ui.report.GraphDisplay;
import seedu.mycrm.ui.report.JobDisplay;

public class ReportWindow extends UiPart<Stage> {

    private static final String FXML = "ReportWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(ReportWindow.class);

    private Stage primaryStage;
    private Logic logic;

    private JobDisplay jobDisplay;
    private GraphDisplay graphDisplay;

    @FXML
    private StackPane jobDisplayPlaceholder;

    @FXML
    private StackPane graphDisplayPlaceholder;

    /**
     * Creates a {@code ReportWindow} with the given {@code Stage} and {@code Logic}.
     */
    public ReportWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

    }

    /**
     * Creates a new ReportWindow.
     */
    public ReportWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        jobDisplay = new JobDisplay();
        jobDisplay.init(logic);
        jobDisplayPlaceholder.getChildren().add(jobDisplay.getRoot());

        graphDisplay = new GraphDisplay();
        graphDisplay.init(logic);
        graphDisplayPlaceholder.getChildren().add(graphDisplay.getRoot());
    }

    /**
     * Shows the report window.
     */
    public void show() {
        logger.fine("Showing report page about the monthly job records.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the report window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the report window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the report window.
     */
    public void focus() {
        getRoot().requestFocus();
    }



}