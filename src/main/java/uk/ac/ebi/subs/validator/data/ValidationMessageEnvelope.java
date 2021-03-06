package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.BaseSubmittable;
import uk.ac.ebi.subs.data.submittable.Submittable;

import java.util.stream.Stream;

/**
 * This is a Data Transfer Object transferring data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the entity to validate.
 * <p>
 * Created by karoly on 08/05/2017.
 */
public class ValidationMessageEnvelope<T extends BaseSubmittable> {

    private String validationResultUUID;
    private int validationResultVersion;
    private T entityToValidate;
    private String submissionId;
    private String dataTypeId;
    private String checklistId;

    public ValidationMessageEnvelope() {
    }

    public ValidationMessageEnvelope(String validationResultUUID, int validationResultVersion, T entityToValidate) {
        this.validationResultUUID = validationResultUUID;
        this.validationResultVersion = validationResultVersion;
        this.entityToValidate = entityToValidate;
    }

    public ValidationMessageEnvelope(String validationResultUUID, int validationResultVersion, T entityToValidate, String submissionId) {
        this.validationResultUUID = validationResultUUID;
        this.validationResultVersion = validationResultVersion;
        this.entityToValidate = entityToValidate;
        this.submissionId = submissionId;
    }

    public ValidationMessageEnvelope(String validationResultUUID, int validationResultVersion, T entityToValidate, String submissionId, String dataTypeId, String checklistId) {
        this.validationResultUUID = validationResultUUID;
        this.validationResultVersion = validationResultVersion;
        this.entityToValidate = entityToValidate;
        this.submissionId = submissionId;
        this.dataTypeId = dataTypeId;
        this.checklistId = checklistId;
    }

    public String getValidationResultUUID() {
        return validationResultUUID;
    }

    public void setValidationResultUUID(String validationResultUUID) {
        this.validationResultUUID = validationResultUUID;
    }

    public int getValidationResultVersion() {
        return validationResultVersion;
    }

    public void setValidationResultVersion(int validationResultVersion) {
        this.validationResultVersion = validationResultVersion;
    }

    public T getEntityToValidate() {
        return entityToValidate;
    }

    public void setEntityToValidate(T entityToValidate) {
        this.entityToValidate = entityToValidate;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(String checklistId) {
        this.checklistId = checklistId;
    }

    public Stream<uk.ac.ebi.subs.validator.model.Submittable> allSubmissionItemsStream() {
        uk.ac.ebi.subs.validator.model.Submittable submittable = new uk.ac.ebi.subs.validator.model.Submittable<T>(entityToValidate, submissionId);
        return Stream.of(submittable);
    }

    public Stream<Submittable> allSubmissionItemsStreamInSubmission() {
        return allSubmissionItemsStream().filter(s -> submissionId.equals(s.getSubmissionId())).map(s -> s.getBaseSubmittable());
    }
}
