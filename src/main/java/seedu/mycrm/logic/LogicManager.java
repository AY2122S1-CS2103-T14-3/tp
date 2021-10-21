package seedu.mycrm.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.parser.MyCrmParser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.products.Product;
import seedu.mycrm.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MyCrmParser myCrmParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        myCrmParser = new MyCrmParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = myCrmParser.parseCommand(commandText);
        model.addHistory(new History(commandText));
        commandResult = command.execute(model);

        try {
            storage.saveMyCrm(model.getMyCrm());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMyCrm getMyCrm() {
        return model.getMyCrm();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public ObservableList<Template> getFilteredTemplateList() {
        return model.getFilteredTemplateList();
    }

    @Override
    public ObservableList<Mail> getFilteredMailList() {
        return model.getFilteredMailList();
    }

    @Override
    public ObservableList<Product> getFilteredProductList() {
        return model.getFilteredProductList();
    }

    @Override
    public ObservableList<Job> getFilteredJobList() {
        return model.getFilteredJobList();
    }

    @Override
    public ObservableList<History> getFilteredHistoryList() {
        return model.getFilteredHistoryList();
    };

    @Override
    public Path getMyCrmFilePath() {
        return model.getMyCrmFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}