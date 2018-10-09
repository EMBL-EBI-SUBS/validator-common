package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.EgaDacPolicy;

/**
 * This is a Data Transfer Object transferring {@link EgaDacPolicy} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link EgaDacPolicy} entity to validate.
 */

public class EgaDacPolicyValidationEnvelopeToCoordinator extends ValidationEnvelopeToCoordinator<EgaDacPolicy> {

}
