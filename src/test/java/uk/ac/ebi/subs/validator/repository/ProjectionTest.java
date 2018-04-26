package uk.ac.ebi.subs.validator.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.subs.validator.data.SingleValidationResult;
import uk.ac.ebi.subs.validator.data.ValidationResult;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This test is not actually testing anything other than being useful to see a projection output.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
public class ProjectionTest {

    @Autowired
    private ProjectionFactory projectionFactory;

    private String entityUuid;

    @Before
    public void setUp() {
        entityUuid = UUID.randomUUID().toString();
    }

    @Test
    public void testOverallStatusProjection() {
        ValidationResultStatus validationResultStatus = projectionFactory.createProjection(ValidationResultStatus.class, createMockValidationResult(entityUuid));
        System.out.println("-> " + validationResultStatus);
    }

    @Configuration
    static class ProjectionConfiguration {

        @Bean
        public SpelAwareProxyProjectionFactory projectionFactory() {
            return new SpelAwareProxyProjectionFactory();
        }
    }

    /** Helper Methods */
    private ValidationResult createMockValidationResult(String entityUuid) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setEntityUuid(entityUuid);
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        validationResult.setVersion(1);

        Map<ValidationAuthor, List<SingleValidationResult>> validationAuthorListMap = new HashMap<>();
        validationAuthorListMap.put(ValidationAuthor.Taxonomy, Arrays.asList(
                generateSingleValidationResult(entityUuid, SingleValidationResultStatus.Error, ValidationAuthor.Taxonomy, "Invalid taxonomy ID.")));
        validationAuthorListMap.put(ValidationAuthor.Biosamples, Arrays.asList(generatePassingSingleValidationResult(entityUuid, ValidationAuthor.Biosamples)));

        validationResult.setExpectedResults(validationAuthorListMap);
        return validationResult;
    }

    private SingleValidationResult generatePassingSingleValidationResult(String entityUUID, ValidationAuthor author) {
        return generateSingleValidationResult(entityUUID, SingleValidationResultStatus.Pass, author, null);
    }

    private SingleValidationResult generateSingleValidationResult(String entityUUID, SingleValidationResultStatus status, ValidationAuthor author, String message) {
        SingleValidationResult singleValidationResult = new SingleValidationResult();
        singleValidationResult.setEntityUuid(entityUUID);
        singleValidationResult.setValidationStatus(status);
        singleValidationResult.setValidationAuthor(author);
        singleValidationResult.setMessage(message);
        return singleValidationResult;
    }

}
