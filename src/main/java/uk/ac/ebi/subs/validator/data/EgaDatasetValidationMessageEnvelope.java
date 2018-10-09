package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.EgaDataset;

public class EgaDatasetValidationMessageEnvelope extends ValidationMessageEnvelope<EgaDataset> {

    public EgaDatasetValidationMessageEnvelope(String validationResultUUID,
                                               int validationResultVersion,
                                               EgaDataset entityToValidate,
                                               String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public EgaDatasetValidationMessageEnvelope() {
    }
}
