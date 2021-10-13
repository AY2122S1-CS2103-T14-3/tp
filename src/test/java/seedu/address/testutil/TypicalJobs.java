package seedu.address.testutil;

import static seedu.address.testutil.TypicalContacts.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MyCrm;
import seedu.address.model.job.Job;

public class TypicalJobs {

    public static final Job COMPLETED = new JobBuilder().withCompletionStatus(true).build();

    public static final Job INCOMPLETE = new JobBuilder().withCompletionStatus(false).build();

    public static final Job BENSON_JOB = new JobBuilder().withClient(BENSON).build();

    private TypicalJobs() {}

    /**
     * Returns an {@code AddressBook} with all the typical jobs.
     */
    public static MyCrm getTypicalAddressBook() {
        MyCrm ab = new MyCrm();
        for (Job job : getTypicalJobs()) {
            ab.addJob(job);
        }
        return ab;
    }

    /**
     * Returns list of typical jobs.
     */
    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(COMPLETED));
    }
}
