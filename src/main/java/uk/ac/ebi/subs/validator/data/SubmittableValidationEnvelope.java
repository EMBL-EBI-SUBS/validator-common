package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.dto.BaseSubmittableDto;

/**
 * A container object holding the submission ID and the entity to validate.
 *
 * Created by karoly on 30/05/2017.
 */

public class SubmittableValidationEnvelope<T extends BaseSubmittableDto> {

    private String submissionId;
    private T entityToValidate;

    public SubmittableValidationEnvelope() {
    }

    public SubmittableValidationEnvelope(String submissionId, T entityToValidate) {
        this.submissionId = submissionId;
        this.entityToValidate = entityToValidate;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public T getEntityToValidate() {
        return entityToValidate;
    }
}
