package uk.ac.ebi.subs.validator.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
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
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableMongoRepositories(basePackageClasses = ValidationResultRepository.class)
@EnableAutoConfiguration
public class ProjectionTest {

    @Autowired
    private ValidationResultRepository repository;
    @Autowired
    private ProjectionFactory projectionFactory;

    private String entityUuid;

    @Before
    public void setUp() {
        entityUuid = UUID.randomUUID().toString();
        createMockValidationResult(entityUuid);
    }

    @Test
    public void testOverallStatusProjection() {
        Stream.of(repository.findByEntityUuid(entityUuid))
                .map(validationResult -> projectionFactory.createProjection(ValidationResultStatus.class, validationResult))
                .forEach(System.out::println);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Configuration
    static class ProjectionConfiguration {

        @Bean
        public SpelAwareProxyProjectionFactory projectionFactory() {
            return new SpelAwareProxyProjectionFactory();
        }
    }

    /** Helper Methods */
    private void createMockValidationResult(String entityUuid) {
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

        repository.save(validationResult);
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
