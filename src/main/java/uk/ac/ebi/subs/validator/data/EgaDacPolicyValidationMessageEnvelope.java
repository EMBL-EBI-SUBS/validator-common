package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.EgaDacPolicy;

public class EgaDacPolicyValidationMessageEnvelope extends ValidationMessageEnvelope<EgaDacPolicy> {

    public EgaDacPolicyValidationMessageEnvelope(String validationResultUUID,
                                                 int validationResultVersion,
                                                 EgaDacPolicy entityToValidate,
                                                 String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public EgaDacPolicyValidationMessageEnvelope() {
    }
}
