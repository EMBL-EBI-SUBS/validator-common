package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.SampleGroup;

/**
 * This is a Data Transfer Object transferring {@link SampleGroup} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link SampleGroup} entity to validate.
 *
 */

public class SampleGroupValidationEnvelopeToCoordinator extends ValidationEnvelopeToCoordinator<SampleGroup> {

}
