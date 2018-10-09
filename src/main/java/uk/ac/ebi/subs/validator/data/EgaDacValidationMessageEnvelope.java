package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.EgaDac;

public class EgaDacValidationMessageEnvelope extends ValidationMessageEnvelope<EgaDac> {

    public EgaDacValidationMessageEnvelope(String validationResultUUID,
                                           int validationResultVersion,
                                           EgaDac entityToValidate,
                                           String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public EgaDacValidationMessageEnvelope() {
    }
}
