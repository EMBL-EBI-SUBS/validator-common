package uk.ac.ebi.subs.validator.messaging;

/**
 * Holds the routing keys of the messages to validate.
 *
 * Created by karoly on 28/04/2017.
 */
public class RoutingKeys {

    public static final String SUBMISSION_VALIDATOR_SUBMISSION_CREATED = "usi.submission.created";
    public static final String SUBMISSION_VALIDATOR_SUBMISSION_UPDATED = "usi.submission.updated";
    
    public static final String SUBMITTABLE_CREATED = "usi.submittable.created";
    public static final String SUBMITTABLE_UPDATED = "usi.submittable.updated";
    
    public static final String SUBMITTABLE_SAMPLE_CREATED = SUBMITTABLE_CREATED + ".sample";
    public static final String SUBMITTABLE_SAMPLE_UPDATED = SUBMITTABLE_UPDATED + ".sample";

    public static final String SUBMITTABLE_STUDY_CREATED = SUBMITTABLE_CREATED + ".study";
    public static final String SUBMITTABLE_STUDY_UPDATED = SUBMITTABLE_UPDATED + ".study";

    public static final String SUBMITTABLE_ASSAY_CREATED = SUBMITTABLE_CREATED + ".assay";
    public static final String SUBMITTABLE_ASSAY_UPDATED = SUBMITTABLE_UPDATED + ".assay";

    public static final String EVENT_BIOSAMPLES_SAMPLE_CREATED = "biosamples.sample.created";
    public static final String EVENT_BIOSAMPLES_SAMPLE_UPDATED = "biosamples.sample.updated";

    public static final String EVENT_ENA_STUDY_CREATED = "ena.study.created";
    public static final String EVENT_ENA_STUDY_UPDATED = "ena.study.updated";
    public static final String EVENT_ENA_SAMPLE_CREATED = "ena.sample.created";
    public static final String EVENT_ENA_SAMPLE_UPDATED = "ena.sample.updated";
    public static final String EVENT_ENA_ASSAY_CREATED = "ena.assay.created";
    public static final String EVENT_ENA_ASSAY_UPDATED = "ena.assay.updated";
    public static final String EVENT_ENA_ASSAYDATA_CREATED = "ena.assaydata.created";
    public static final String EVENT_ENA_ASSAYDATA_UPDATED = "ena.assaydata.updated";

    public static final String EVENT_TAXON_SAMPLE_CREATED = "taxon.sample.created";
    public static final String EVENT_TAXON_SAMPLE_UPDATED = "taxon.sample.updated";

    // core-validator routing keys
    public static final String EVENT_CORE_ASSAY_VALIDATION = "core.assay.validation";
    public static final String EVENT_CORE_ASSAYDATA_VALIDATION = "core.assaydata.validation";
    public static final String EVENT_CORE_SAMPLE_VALIDATION = "core.sample.validation";
    public static final String EVENT_CORE_STUDY_VALIDATION = "core.study.validation";

    public static final String EVENT_AE_SAMPLE_CREATED = "ae.sample.created";
    public static final String EVENT_AE_SAMPLE_UPDATED = "ae.sample.updated";

    public static final String EVENT_VALIDATION_SUCCESS = "validation.success";
    public static final String EVENT_VALIDATION_ERROR = "validation.error";

    public static final String EVENT_VALIDATION_RESULT_DOCUMENT_UPDATED = "validationresult.updated";
}
