package uk.ac.ebi.subs.validator.data;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * Validation result document to store all the validation results
  */
@ToString(callSuper = true)
@Document
@CompoundIndexes({
    @CompoundIndex(name = "submission_entity_id", def = "{'submissionId': 1, 'entityUuid': 1}"),
    @CompoundIndex(name = "entity_uuid", def = "{'entityUuid': 1}")
}
)
public class ValidationResult extends AbstractValidationResult implements Identifiable {

    @Id
    private String uuid;
    private int version;

    @Indexed
    private String submissionId;

    private Map<ValidationAuthor, List<SingleValidationResult>> expectedResults = new HashMap<>();

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
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
}
