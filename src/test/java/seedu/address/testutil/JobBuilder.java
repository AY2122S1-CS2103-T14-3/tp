package seedu.address.testutil;

import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalProducts.ASUS_GPU;

import seedu.address.model.contact.Contact;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobDeliveryDate;
import seedu.address.model.job.JobDescription;
import seedu.address.model.products.Product;

/**
 * A utility class to help with building Job objects.
 */
public class JobBuilder {

    private static final String DEFAULT_JOB_DESCRIPTION = "Some description";
    private static final boolean DEFAULT_COMPLETION_STATUS = false;
    private static final String DEFAULT_DELIVERY_DATE = "12/12/2021";

    private JobDescription jobDescription;
    private Contact client;
    private Product product;
    private JobDeliveryDate deliveryDate;
    private boolean completionStatus;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        jobDescription = new JobDescription(DEFAULT_JOB_DESCRIPTION);
        deliveryDate = new JobDeliveryDate(DEFAULT_DELIVERY_DATE);
        completionStatus = DEFAULT_COMPLETION_STATUS;
        client = ALICE;
        product = ASUS_GPU;
    }

    /**
     * Initializes the JobBuilder with the data of {@code jobToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        jobDescription = jobToCopy.getJobDescription();
        deliveryDate = jobToCopy.getDeliveryDate();
        client = jobToCopy.getClient();
        product = jobToCopy.getProduct();
        completionStatus = jobToCopy.isCompleted();
    }

    /**
     * Sets the {@code JobDescription} of the {@code Job} that we are building.
     */
    public JobBuilder withJobDescription(String jobDescription) {
        this.jobDescription = new JobDescription(jobDescription);
        return this;
    }

    /**
     * Sets the {@code deliveryDate} of the {@code Job} that we are building.
     */
    public JobBuilder withDeliveryDate(String deliveryDate) {
        this.deliveryDate = new JobDeliveryDate(deliveryDate);
        return this;
    }

    /**
     * Sets the {@code client} of the {@code Job} that we are building.
     */
    public JobBuilder withClient(Contact client) {
        this.client = client;
        return this;
    }

    /**
     * Sets the {@code product} of the {@code Job} that we are building.
     */
    public JobBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    /**
     * Sets the {@code completionStatus} of the {@code Job} that we are building.
     */
    public JobBuilder withCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
        return this;
    }

    public Job build() {
        return new Job(jobDescription, client, product, deliveryDate, completionStatus);
    }
}
