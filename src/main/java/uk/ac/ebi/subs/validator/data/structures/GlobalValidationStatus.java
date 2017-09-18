package uk.ac.ebi.subs.validator.data.structures;

/**
 * Controlled vocabulary for the submittable global validation result status
 *
 *  <li>{@link #Pending}</li>
 *  <li>{@link #Complete}</li>
 */
public enum GlobalValidationStatus {
    /**
     * Waiting for single validation results
     */
    Pending,
    /**
     * All single validation results are accounted for
     */
    Complete
}
