package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Project;

/**
 * This is a Data Transfer Object transferring {@link Project} data from the main submission application
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link Project} entity to validate.
 *
 */

public class SubmittedProjectValidationEnvelope extends SubmittableValidationEnvelope<Project> {

}
