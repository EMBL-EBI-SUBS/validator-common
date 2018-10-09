package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.EgaDataset;

/**
 * This is a Data Transfer Object transferring {@link EgaDataset} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link EgaDataset} entity to validate.
 */

public class EgaDatasetValidationEnvelopeToCoordinator extends ValidationEnvelopeToCoordinator<EgaDataset> {

}
