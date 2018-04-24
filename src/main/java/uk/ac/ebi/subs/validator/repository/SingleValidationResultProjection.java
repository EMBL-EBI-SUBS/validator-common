package uk.ac.ebi.subs.validator.repository;

import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.subs.validator.data.SingleValidationResult;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;

@Projection(name = "singleValidationResult", types = SingleValidationResult.class)
public interface SingleValidationResultProjection {

    SingleValidationResultStatus getValidationStatus();
    String getMessage();
}
