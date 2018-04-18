package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.BaseSubmittable;

/**
 * This is a Data Transfer Object transferring data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the entity to validate.
 *
 * Created by karoly on 30/05/2017.
 */

public class ValidationEnvelopeToCoordinator<T extends BaseSubmittable> {

    private String submissionId;
    private T entityToValidate;

    public ValidationEnvelopeToCoordinator() {
    }

    public ValidationEnvelopeToCoordinator(String submissionId, T entityToValidate) {
        this.submissionId = submissionId;
        this.entityToValidate = entityToValidate;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public T getEntityToValidate() {
        return entityToValidate;
    }
    
    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public void setEntityToValidate(T entityToValidate) {
        this.entityToValidate = entityToValidate;
    }
}
