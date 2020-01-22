package uk.ac.ebi.subs.validator.schema;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.subs.validator.schema.model.JsonSchemaValidationError;
import uk.ac.ebi.subs.validator.schema.model.JsonSchemaValidationRequestBody;
import uk.ac.ebi.subs.validator.schema.model.JsonSchemaValidatorException;

import java.util.Arrays;
import java.util.List;

@Data
public class JsonSchemaValidationService {
    private static final Logger logger = LoggerFactory.getLogger(JsonSchemaValidationService.class);

    private String jsonSchemaValidator;

    private RestTemplate restTemplate;

    public JsonSchemaValidationService(String jsonSchemaValidatorURL, RestTemplate restTemplate) {
        this.jsonSchemaValidator = jsonSchemaValidatorURL;
        this.restTemplate = restTemplate;
    }

    public List<JsonSchemaValidationError> validate(JsonNode schema, JsonNode submittable) {
        logger.trace("Calling json-schema-validator...");
        ResponseEntity<JsonSchemaValidationError[]> response;

        try {
            response = restTemplate.postForEntity(jsonSchemaValidator, new JsonSchemaValidationRequestBody(schema, submittable), JsonSchemaValidationError[].class);
        } catch (RestClientException e) {
            throw new JsonSchemaValidatorException(e.getMessage(), e);
        }

        if(response.getStatusCode() != HttpStatus.OK) {
            throw new JsonSchemaValidatorException(response.getStatusCode().getReasonPhrase());
        }

        return Arrays.asList(response.getBody());
    }

}
