package seedu.address.logic.commands.jobs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalJobs.BENSON_JOB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;
import seedu.address.testutil.TypicalJobs;

public class AddJobIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalJobs.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newJob_success() {
        Job validJob = BENSON_JOB;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addJob(validJob);

        assertCommandSuccess(new AddJobCommand(validJob, null, null), model,
            String.format(AddJobCommand.MESSAGE_SUCCESS, validJob), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Job jobInList = model.getAddressBook().getJobList().get(0);
        assertCommandFailure(new AddJobCommand(jobInList, null, null),
                model, AddJobCommand.MESSAGE_DUPLICATE_JOB);
    }
}
