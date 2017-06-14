package uk.ac.ebi.subs.validator.data;

import lombok.ToString;

/**
  * Validation entity result document to store an entity validation result for a specific rule set
  */
@ToString(callSuper = true)
public class SingleValidationResult extends AbstractValidationResult implements Identifiable {

    private int version;
    private String uuid;
    private String validationResultUUID;

    private ValidationAuthor validationAuthor;

    public SingleValidationResult() {
    }

    public SingleValidationResult(ValidationAuthor validationAuthor, String entityUuid) {
        this.validationAuthor = validationAuthor;
        this.setEntityUuid(entityUuid);
        this.setValidationStatus(ValidationStatus.Pending);
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
