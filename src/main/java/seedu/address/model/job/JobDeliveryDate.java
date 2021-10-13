package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JobDeliveryDate {
    public static final String MESSAGE_CONSTRAINTS = "Job Delivery Date should follow the format dd/MM/YYYY";

    private static final DateTimeFormatter VALID_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public final LocalDate value;

    /**
     * Constructs a {@code JobDeliveryDate}.
     *
     * @param deliveryDate A string representation of the delivery date.
     */
    public JobDeliveryDate(String deliveryDate) {
        requireNonNull(deliveryDate);
        checkArgument(isValidJobDeliveryDate(deliveryDate), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(deliveryDate, VALID_INPUT_FORMAT);;
    }

    /**
     * Returns true if the given string for the delivery date conforms to the correct format.
     */
    public static boolean isValidJobDeliveryDate(String test) {
        try {
            LocalDate.parse(test, VALID_INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(DISPLAY_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobDeliveryDate // instanceof handles nulls
            && value.equals(((JobDeliveryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
