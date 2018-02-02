package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Sample;

/**
 * This is a Data Transfer Object transferring {@link Sample} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Sample} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class SampleValidationMessageEnvelope extends ValidationMessageEnvelope<Sample> {
    public SampleValidationMessageEnvelope() {
    }

    public SampleValidationMessageEnvelope(String validationResultUUID,
                                           int validationResultVersion,
                                           Sample entityToValidate,
                                           String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }
}
