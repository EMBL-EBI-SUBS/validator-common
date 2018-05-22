package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.fileupload.File;
import uk.ac.ebi.subs.data.submittable.BaseSubmittable;
import uk.ac.ebi.subs.data.submittable.Submittable;

import java.util.Collections;
import java.util.stream.Stream;

/**
 * This is a Data Transfer Object transferring data from the {@code file-upload-service} service
 * to the file reference validator.
 * A container object holding the UUID (MongoDB ID) and the file to validate.
 *
 * Created by karoly on 07/04/2018.
 */
public class FileUploadValidationMessageEnvelope {

    private String validationResultUUID;
    private int validationResultVersion;
    private File fileToValidate;
    private String submissionId;

    public FileUploadValidationMessageEnvelope() {
    }

    public FileUploadValidationMessageEnvelope(String validationResultUUID, int validationResultVersion, File fileToValidate, String submissionId) {
        this.validationResultUUID = validationResultUUID;
        this.validationResultVersion = validationResultVersion;
        this.fileToValidate = fileToValidate;
        this.submissionId = submissionId;
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

    public File getfileToValidate() {
        return fileToValidate;
    }

    public void setfileToValidate(File fileToValidate) {
        this.fileToValidate = fileToValidate;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }
}
