package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Sample;

/**
 * This is a Data Transfer Object transferring {@link Sample} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link Sample} entity to validate.
 *
 * Created by karoly on 6/10/2017.
 */

public class SampleValidationEnvelopeToCoordinator extends ValidationEnvelopeToCoordinator<Sample> {

}
