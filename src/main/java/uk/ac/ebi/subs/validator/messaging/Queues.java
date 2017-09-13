package uk.ac.ebi.subs.validator.messaging;

/**
 * Holds the name of the queues related to validation.
 *
 * Created by karoly on 28/04/2017.
 */
public class Queues {

    public static final String ENA_STUDY_VALIDATION = "ena-study-validation";
    public static final String ENA_SAMPLE_VALIDATION = "ena-sample-validation";
    public static final String ENA_ASSAY_VALIDATION = "ena-assay-validation";
    public static final String ENA_ASSAYDATA_VALIDATION = "ena-assaydata-validation";

    public static final String TAXON_SAMPLE_VALIDATION = "taxon-sample-validation";
    
    public static final String BIOSAMPLES_SAMPLE_VALIDATION = "biosamples-sample-validation";

    public static final String CORE_ASSAY_VALIDATION = "core-assay-validation";
    public static final String CORE_ASSAYDATA_VALIDATION = "core-assaydata-validation";
    public static final String CORE_SAMPLE_VALIDATION = "core-sample-validation";
    public static final String CORE_STUDY_VALIDATION = "core-study-validation";

    public static final String VALIDATION_RESULT = "validation-result";

    public static final String VALIDATION_RESULT_DOCUMENT_UPDATE = "validation-result-document-update";

    public static final String SUBMISSION_SAMPLE_VALIDATOR = "usi-submission-sample-validator";
    public static final String SUBMISSION_STUDY_VALIDATOR = "usi-submission-study-validator";
    public static final String SUBMISSION_ASSAY_VALIDATOR = "usi-submission-assay-validator";
    public static final String SUBMISSION_ASSAY_DATA_VALIDATOR = "usi-submission-assaydata-validator";
}
