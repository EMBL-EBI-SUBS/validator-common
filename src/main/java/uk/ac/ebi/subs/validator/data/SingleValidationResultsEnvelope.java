package uk.ac.ebi.subs.validator.data;

import java.util.List;

/**
 * This is a Data Transfer Object transferring data from a {@code Validator} to the {@code validator-aggregator} service.
 *
 * Created by karoly on 07/07/2017.
 */
public class SingleValidationResultsEnvelope {

    private List<SingleValidationResult> singleValidationResults;
    private String validationResultVersion;
    private String validationResultUUID;
    private ValidationAuthor validationAuthor;

    /**
     * Constructor of the {@code SingleValidationResultsEnvelope} object.
     *
     * @param singleValidationResults validation result(s) returned from a specific validator
     * @param validationResultVersion the version of the {@link ValidationResult} the validation relates to
     * @param validationResultUUID the UUID of the {@link ValidationResult} the validation relates to
     * @param validationAuthor the author of the validation
     */
    public SingleValidationResultsEnvelope(List<SingleValidationResult> singleValidationResults, String validationResultVersion,
                                           String validationResultUUID, ValidationAuthor validationAuthor) {
        this.singleValidationResults = singleValidationResults;
        this.validationResultVersion = validationResultVersion;
        this.validationResultUUID = validationResultUUID;
        this.validationAuthor = validationAuthor;
    }

    public List<SingleValidationResult> getSingleValidationResults() {
        return singleValidationResults;
    }

    public String getValidationResultVersion() {
        return validationResultVersion;
    }

    public String getValidationResultUUID() {
        return validationResultUUID;
    }

    public ValidationAuthor getValidationAuthor() {
        return validationAuthor;
    }
}