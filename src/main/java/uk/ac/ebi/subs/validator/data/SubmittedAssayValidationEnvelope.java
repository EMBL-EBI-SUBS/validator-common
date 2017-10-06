package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Assay;

/**
 * This is a Data Transfer Object transferring {@link uk.ac.ebi.subs.data.submittable.Assay} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link uk.ac.ebi.subs.data.submittable.Assay} entity to validate.
 *
 * Created by karoly on 6/10/2017.
 */

public class SubmittedAssayValidationEnvelope extends SubmittableValidationEnvelope<Assay> {

}
