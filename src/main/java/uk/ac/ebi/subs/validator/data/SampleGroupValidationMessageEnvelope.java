package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.SampleGroup;

public class SampleGroupValidationMessageEnvelope extends ValidationMessageEnvelope<SampleGroup> {

    public SampleGroupValidationMessageEnvelope(String validationResultUUID,
                                                int validationResultVersion,
                                                SampleGroup entityToValidate,
                                                String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public SampleGroupValidationMessageEnvelope() {
    }
}
