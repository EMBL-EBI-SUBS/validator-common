package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Protocol;

public class ProtocolValidationMessageEnvelope extends ValidationMessageEnvelope<Protocol> {

    public ProtocolValidationMessageEnvelope(String validationResultUUID,
                                             int validationResultVersion,
                                             Protocol entityToValidate,
                                             String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public ProtocolValidationMessageEnvelope() {
    }
}
