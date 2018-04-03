package uk.ac.ebi.subs.validator.data;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

/**
  * Validation entity result document to store an entity validation result for a specific rule set
  */
@ToString
@EqualsAndHashCode
public class SingleValidationResult {

    private ValidationAuthor validationAuthor;

    private SingleValidationResultStatus validationStatus = SingleValidationResultStatus.Pending;

    private String message;

    private String entityUuid;

    public SingleValidationResult() {}

    public SingleValidationResult(ValidationAuthor validationAuthor, String entityUuid) {
        this.validationAuthor = validationAuthor;
        this.entityUuid = entityUuid;
    }

    public ValidationAuthor getValidationAuthor() {
        return validationAuthor;
    }

    public void setValidationAuthor(ValidationAuthor validationAuthor) {
        this.validationAuthor = validationAuthor;
    }

    public SingleValidationResultStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(SingleValidationResultStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntityUuid() {
        return entityUuid;
    }

    public void setEntityUuid(String entityUuid) {
        this.entityUuid = entityUuid;
    }
}
