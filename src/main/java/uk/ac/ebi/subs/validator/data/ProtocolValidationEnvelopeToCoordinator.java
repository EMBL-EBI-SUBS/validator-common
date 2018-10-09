package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Protocol;

/**
 * This is a Data Transfer Object transferring {@link Protocol} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link Protocol} entity to validate.
 */

public class ProtocolValidationEnvelopeToCoordinator extends ValidationEnvelopeToCoordinator<Protocol> {

}
