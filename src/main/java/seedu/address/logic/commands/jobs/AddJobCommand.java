package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.job.Job;
import seedu.address.model.products.Product;

public class AddJobCommand extends Command {
    public static final String COMMAND_WORD = "addJob";

    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Adds a repair job to MyCRM.\n"
        + "Parameters: "
        + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION "
        + "[ " + PREFIX_CONTACT_INDEX + "CONTACT INDEX ]"
        + "[ " + PREFIX_PRODUCT_INDEX + "PRODUCT INDEX ]"
        + "[ " + PREFIX_DELIVERY_DATE + "DELIVERY DATE ]\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_JOB_DESCRIPTION + "Graphics card replacement needed "
        + PREFIX_CONTACT_INDEX + "3 "
        + PREFIX_PRODUCT_INDEX + "1 "
        + PREFIX_DELIVERY_DATE + "15/09/2021 ";

    public static final String MESSAGE_SUCCESS = "New repair job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This repair job already exists in the MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Job toAdd;
    private final Index contactIndex;
    private final Index productIndex;

    /**
     * Creates an AddJobCommand to add the specified {@code Job}
     */
    public AddJobCommand(Job job, Index contactIndex, Index productIndex) {
        requireNonNull(job);
        this.contactIndex = contactIndex;
        this.productIndex = productIndex;
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        linkContactToJob(toAdd, model.getFilteredContactList());
        linkProductToJob(toAdd, model.getFilteredProductList());

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }

    private void linkContactToJob(Job job, List<Contact> lastShownContactList) throws CommandException {
        if (contactIndex != null) {
            if (contactIndex.getZeroBased() >= lastShownContactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }

            Contact client = lastShownContactList.get(contactIndex.getZeroBased());
            job.setClient(client);
        }
    }

    private void linkProductToJob(Job job, List<Product> lastShownProductList) throws CommandException {
        if (productIndex != null) {
            if (productIndex.getZeroBased() >= lastShownProductList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
            }

            Product product = lastShownProductList.get(productIndex.getZeroBased());
            job.setProduct(product);
        }
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddJobCommand // instanceof handles nulls
            && toAdd.equals(((AddJobCommand) other).toAdd));
    }
}
