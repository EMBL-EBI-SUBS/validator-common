package uk.ac.ebi.subs.validator.data;

import lombok.ToString;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

/**
  * Validation entity result document to store an entity validation result for a specific rule set
  */
@ToString
public class SingleValidationResult {

    private String validationResultUUID;

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

    public String getValidationResultUUID() {
        return validationResultUUID;
    }

    public void setValidationResultUUID(String validationResultUUID) {
        this.validationResultUUID = validationResultUUID;
    }
}
