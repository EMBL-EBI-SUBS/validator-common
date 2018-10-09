package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.EgaDac;

/**
 * This is a Data Transfer Object transferring {@link EgaDac} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link EgaDac} entity to validate.
 */

public class EgaDacValidationEnvelopeToCoordinator extends ValidationEnvelopeToCoordinator<EgaDac> {

}
