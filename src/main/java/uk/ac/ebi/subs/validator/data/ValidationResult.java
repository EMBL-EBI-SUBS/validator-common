package uk.ac.ebi.subs.validator.data;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
  * Validation result document to store all the validation results
  */
@ToString
@Document
@CompoundIndexes({
    @CompoundIndex(name = "submission_entity_id", def = "{'submissionId': 1, 'entityUuid': 1}"),
    @CompoundIndex(name = "entity_uuid", def = "{'entityUuid': 1}")
})
public class ValidationResult {

    @Id
    private String uuid;

    private int version;

    private String entityType;

    private String entityUuid;

    private String dataTypeId;

    private GlobalValidationStatus validationStatus = GlobalValidationStatus.Pending;

    private String message;

    @Indexed
    private String submissionId;

    private Map<ValidationAuthor, List<SingleValidationResult>> expectedResults = new HashMap<>();

    /*
    This 2 fields are set when the validationStatus is set to Complete
    and are reset when the validationStatus is set to Pending.
    */
    private Map<ValidationAuthor, String> overallValidationOutcomeByAuthor = new TreeMap<>();
    private Map<ValidationAuthor, List<String>> errorMessages = new TreeMap<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityUuid() {
        return entityUuid;
    }

    public void setEntityUuid(String entityUuid) {
        this.entityUuid = entityUuid;
    }

    public String getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public GlobalValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(GlobalValidationStatus validationStatus) {
        if (validationStatus.equals(GlobalValidationStatus.Pending)) {
            this.overallValidationOutcomeByAuthor = new TreeMap<>();
            this.errorMessages = new TreeMap<>();
        } else if (validationStatus.equals(GlobalValidationStatus.Complete)) {
            computeOverallValidationOutcomeByAuthor();
            exposeErrorAndWarningMessages();
        }
        this.validationStatus = validationStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public Map<ValidationAuthor, List<SingleValidationResult>> getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(Map<ValidationAuthor, List<SingleValidationResult>> expectedResults) {
        this.expectedResults = expectedResults;
    }

    public Map<ValidationAuthor, String> getOverallValidationOutcomeByAuthor() {
        return Collections.unmodifiableMap(this.overallValidationOutcomeByAuthor);
    }

    public Map<ValidationAuthor, List<String>> getErrorMessages() {
        return Collections.unmodifiableMap(this.errorMessages);
    }


    private void computeOverallValidationOutcomeByAuthor() {
        Map<ValidationAuthor, String> tempMap = new TreeMap<>();
        this.expectedResults.entrySet().forEach(entry -> tempMap.put(entry.getKey(), getOverallValidationOutcome(entry.getValue()).toString()));
        this.overallValidationOutcomeByAuthor = new TreeMap<>(tempMap);
    }

    private SingleValidationResultStatus getOverallValidationOutcome(List<SingleValidationResult> singleValidationResults) {
        SingleValidationResultStatus status = SingleValidationResultStatus.Pass;
        for (SingleValidationResult singleValidationResult : singleValidationResults) {
            if (singleValidationResult.getValidationStatus().equals(SingleValidationResultStatus.Error)) {
                status = SingleValidationResultStatus.Error;
                return status;
            } else if (singleValidationResult.getValidationStatus().equals(SingleValidationResultStatus.Warning)) {
                status = SingleValidationResultStatus.Warning;
            }
        }
        return status;
    }

    private void exposeErrorAndWarningMessages() {
        Map<ValidationAuthor, List<String>> errorMessagesByAuthor = new TreeMap<>();
        this.expectedResults.entrySet().forEach(entry -> errorMessagesByAuthor.put(entry.getKey(), getErrorAndWarningMessages(entry.getValue())));
        this.errorMessages = new TreeMap<>(errorMessagesByAuthor);
    }

    private List<String> getErrorAndWarningMessages(List<SingleValidationResult> singleValidationResults) {
        return singleValidationResults.stream()
                .filter(svr -> svr.getValidationStatus().equals(SingleValidationResultStatus.Error)
                        || svr.getValidationStatus().equals(SingleValidationResultStatus.Warning))
                .map(svr -> svr.getMessage())
                .collect(Collectors.toList());
    }

}
