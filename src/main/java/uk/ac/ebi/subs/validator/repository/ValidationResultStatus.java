package uk.ac.ebi.subs.validator.repository;

import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.subs.validator.data.ValidationResult;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

import java.util.List;
import java.util.Map;

@Projection(name = "overallStatus", types = ValidationResult.class)
public interface ValidationResultStatus {

    GlobalValidationStatus getValidationStatus();
    String getMessage();
    Map<ValidationAuthor, String> getOverallValidationOutcomeByAuthor();
    Map<ValidationAuthor, List<String>> getErrorMessages();
}
