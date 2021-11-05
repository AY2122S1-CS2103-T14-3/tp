package seedu.insurancepal.logic.commands;

import static seedu.insurancepal.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.insurancepal.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.person.Revenue;

/**
 * Updates the revenue of an existing client in the address book.
 */
public class RevenueCommand extends Command {

    public static final String COMMAND_WORD = "revenue";

    public static final String MESSAGE_ADD_REVENUE_SUCCESS = "Added revenue to Person: %1$s";
    public static final String MESSAGE_ADD_REVENUE_FAIL = "Failed to add revenue from Person: %1$s, "
            + "resulting revenue is negative!";

    private final Index index;
    private final Revenue revenue;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param revenue of the person to be updated to
     */
    public RevenueCommand(Index index, Revenue revenue) {
        requireAllNonNull(index, revenue);

        this.index = index;
        this.revenue = revenue;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                this.revenue.addRevenue(personToEdit.getRevenue()),
                personToEdit.getAddress(), personToEdit.getTags(),
                personToEdit.getInsurances(), personToEdit.getNote(),
                personToEdit.getAppointment(), personToEdit.getClaims());

        if (!editedPerson.getRevenue().isValidResultingRevenue()) {
            throw new CommandException(String.format(MESSAGE_ADD_REVENUE_FAIL, personToEdit));
        }
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_REVENUE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RevenueCommand // instanceof handles nulls
                && revenue.equals(((RevenueCommand) other).revenue) && index.equals(((RevenueCommand) other).index));
    }
}
