package uk.ac.ebi.subs.validator.data.structures;

/**
 * Controlled vocabulary for single validation result status
 *
 * <li>{@link #Pending}</li>
 * <li>{@link #Pass}</li>
 * <li>{@link #Error}</li>
 * <li>{@link #Warning}</li>
 */
public enum SingleValidationResultStatus {
    /**
     * Waiting for validation result
     */
    Pending,
    /**
     * We will accept this
     */
    Pass,
    /**
     * We will not accept this
     */
    Error,
    /**
     * We will accept this, but you may wish to reconsider
     */
    Warning
}
