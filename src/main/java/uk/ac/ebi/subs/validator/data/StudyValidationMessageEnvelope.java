package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Project;
import uk.ac.ebi.subs.data.submittable.Study;
import uk.ac.ebi.subs.validator.model.Submittable;

/**
 * This is a Data Transfer Object transferring {@link Study} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Study} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class StudyValidationMessageEnvelope extends ValidationMessageEnvelope<Study> {

    public StudyValidationMessageEnvelope(String validationResultUUID,
                                          int validationResultVersion,
                                          Study entityToValidate,
                                          String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate,submissionId);
    }

    public StudyValidationMessageEnvelope() {
    }

    private Submittable<Project> project;

    public Submittable<Project> getProject() {
        return project;
    }

    public void setProject(Submittable<Project> project) {
        this.project = project;
    }
}
