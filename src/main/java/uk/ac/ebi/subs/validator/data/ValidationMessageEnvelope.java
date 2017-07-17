package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.BaseSubmittable;

/**
 * This is a Data Transfer Object transferring data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the entity to validate.
 *
 * Created by karoly on 08/05/2017.
 */
public class ValidationMessageEnvelope<T extends BaseSubmittable> {

    private String validationResultUUID;
    private int validationResultVersion;
    private T entityToValidate;

    public ValidationMessageEnvelope() {
    }

    public ValidationMessageEnvelope(String validationResultUUID, int validationResultVersion, T entityToValidate) {
        this.validationResultUUID = validationResultUUID;
        this.validationResultVersion = validationResultVersion;
        this.entityToValidate = entityToValidate;
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
}
