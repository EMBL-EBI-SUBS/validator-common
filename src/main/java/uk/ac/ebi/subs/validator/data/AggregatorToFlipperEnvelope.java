package uk.ac.ebi.subs.validator.data;

/**
 * This is a Data Transfer Object transferring data from the {@code validator-aggregator} to the {@code validator-status-flipper} service.
 *
 */
public class AggregatorToFlipperEnvelope {

    private String validationResultUuid;
    private int validationResultVersion;

    /**
     * Constructor of the {@code AggregatorToFlipperEnvelope} object.
     *
     * @param validationResultUuid the UUID of the {@link ValidationResult} the validation relates to
     * @param validationResultVersion the version of the {@link ValidationResult} the validation relates to
     */
    public AggregatorToFlipperEnvelope(String validationResultUuid, int validationResultVersion) {
        this.validationResultUuid = validationResultUuid;
        this.validationResultVersion = validationResultVersion;
    }

    public String getValidationResultUuid() {
        return validationResultUuid;
    }

    public void setValidationResultUuid(String validationResultUuid) {
        this.validationResultUuid = validationResultUuid;
    }

    public int getValidationResultVersion() {
        return validationResultVersion;
    }

    public void setValidationResultVersion(int validationResultVersion) {
        this.validationResultVersion = validationResultVersion;
    }
}
