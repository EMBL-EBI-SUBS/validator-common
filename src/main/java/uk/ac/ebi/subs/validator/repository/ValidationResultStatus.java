package uk.ac.ebi.subs.validator.repository;

import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.subs.validator.data.ValidationResult;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;

@Projection(name = "overallStatus",types = ValidationResult.class)
public interface ValidationResultStatus {

    GlobalValidationStatus getValidationStatus();
    String getMessage();

}
