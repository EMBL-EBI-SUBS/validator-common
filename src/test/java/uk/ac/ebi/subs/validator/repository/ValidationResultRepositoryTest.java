package uk.ac.ebi.subs.validator.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.subs.validator.data.ValidationAuthor;
import uk.ac.ebi.subs.validator.data.ValidationResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ValidationResultRepository.class)
@EnableAutoConfiguration
public class ValidationResultRepositoryTest {

    public static final String SUBMISSION_ID_1 = "123";
    public static final String SUBMISSION_ID_2 = "456";
    public static final String ENTITY_UUID_1 = "44566";
    public static final String ENTITY_UUID_2 = "98876";
    public static final String ENTITY_UUID_3 = "11223";

    @Autowired
    ValidationResultRepository validationResultRepository;

    private ValidationResult validationResult;

    @Before
    public void buildUp() {
        Map<ValidationAuthor, Boolean> expectedResults = new HashMap<>();
        expectedResults.put(ValidationAuthor.Biosamples, true);
        expectedResults.put(ValidationAuthor.Taxonomy, false);
        expectedResults.put(ValidationAuthor.Ena, false);

        // First
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setVersion(1);
        validationResult.setSubmissionId(SUBMISSION_ID_1);
        validationResult.setEntityUuid(ENTITY_UUID_1);

        validationResultRepository.insert(validationResult);

        // Second
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setSubmissionId(SUBMISSION_ID_2);
        validationResult.setEntityUuid(ENTITY_UUID_2);

        validationResultRepository.insert(validationResult);

        // Third
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setSubmissionId(SUBMISSION_ID_1);
        validationResult.setEntityUuid(ENTITY_UUID_3);

        validationResultRepository.insert(validationResult);
    }

    @Test
    public void persistValidationResultTest() {
        ValidationResult retrievedResult = validationResultRepository.findOne(validationResult.getUuid());
        System.out.println(retrievedResult);

        assertThat(retrievedResult.getExpectedResults().get(ValidationAuthor.Biosamples), is(true));
    }

    @Test
    public void findBySubmissionIdAndEntityUuidTest() {
        List<ValidationResult> validationResults = validationResultRepository.findBySubmissionIdAndEntityUuid("123", "44566");
        System.out.println(validationResults);

        Assert.assertEquals(1, validationResults.size());
    }

    @Test
    public void findByEntityUuidTest() {
        ValidationResult actualValidationResult = validationResultRepository.findByEntityUuid(ENTITY_UUID_1);

        assertThat(actualValidationResult.getSubmissionId(), is(equalTo(SUBMISSION_ID_1)));
    }

    @Test
    public void findBySubmissionIdTest() {
        List<ValidationResult> actualValidationResults = validationResultRepository.findBySubmissionId(SUBMISSION_ID_1);

        assertThat(actualValidationResults.size(), is(equalTo(2)));
        assertThat(actualValidationResults.get(0).getEntityUuid(), is(equalTo(ENTITY_UUID_3)));
        assertThat(actualValidationResults.get(1).getEntityUuid(), is(equalTo(ENTITY_UUID_1)));
    }

    @After
    public void tearDown() {
        validationResultRepository.deleteAll();
    }

}