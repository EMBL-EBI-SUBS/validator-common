package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.fileupload.File;

/**
 * This is a Data Transfer Object transferring {@link File} data from the file upload service
 * to the {@code validator-coordinator} service.
 * A container object holding the submission ID and the {@link File} object to validate.
 *
 * Created by karoly on 18/04/2018.
 */

public class FileUploadValidationEnvelopeToCoordinator {

    private String submissionId;
    private File fileToValidate;

    public FileUploadValidationEnvelopeToCoordinator() {
    }

    public FileUploadValidationEnvelopeToCoordinator(String submissionId, File fileToValidate) {
        this.submissionId = submissionId;
        this.fileToValidate = fileToValidate;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public File getFileToValidate() {
        return fileToValidate;
    }

    public void setFileToValidate(File fileToValidate) {
        this.fileToValidate = fileToValidate;
    }
}
