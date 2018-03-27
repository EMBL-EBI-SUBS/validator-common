package uk.ac.ebi.subs.validator.data;


import uk.ac.ebi.subs.data.submittable.Project;

/**
 * This is a Data Transfer Object transferring {@link Project} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Project} entity to validate.
 *
 */
public class ProjectValidationMessageEnvelope extends ValidationMessageEnvelope<Project> {

    public ProjectValidationMessageEnvelope(String validationResultUUID,
                                            int validationResultVersion,
                                            Project entityToValidate,
                                            String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate,submissionId);
    }

    public ProjectValidationMessageEnvelope() {
    }
}
