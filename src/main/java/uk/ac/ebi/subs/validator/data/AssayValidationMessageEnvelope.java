package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Assay;

/**
 * This is a Data Transfer Object transferring {@link Assay} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Assay} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class AssayValidationMessageEnvelope extends ValidationMessageEnvelope<Assay> {

}
