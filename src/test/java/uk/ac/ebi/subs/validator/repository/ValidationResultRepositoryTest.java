package uk.ac.ebi.subs.validator.repository;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.ac.ebi.subs.validator.data.SingleValidationResult;
import uk.ac.ebi.subs.validator.data.ValidationAuthor;
import uk.ac.ebi.subs.validator.data.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ValidationResultRepository.class)
@EnableAutoConfiguration
public class ValidationResultRepositoryTest {

    private static final String SUBMISSION_ID_1 = "123";
    private static final String SUBMISSION_ID_2 = "456";
    private static final String SUBMISSION_ID_INVALID = "invalid submission id";
    private static final String ENTITY_UUID_1 = "44566";
    private static final String ENTITY_UUID_2 = "98876";
    private static final String ENTITY_UUID_3 = "11223";
    private static final String ENTITY_UUID_INVALID = "invalid entity uuid";

    @Autowired
    ValidationResultRepository validationResultRepository;

    private ValidationResult validationResult;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void buildUp() {
        Map<ValidationAuthor, List<SingleValidationResult>> expectedResults = new HashMap<>();
        expectedResults.put(ValidationAuthor.Biosamples, new ArrayList<>());
        expectedResults.put(ValidationAuthor.Taxonomy, new ArrayList<>());
        expectedResults.put(ValidationAuthor.Ena, new ArrayList<>());

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

        assertThat(retrievedResult.getExpectedResults().get(ValidationAuthor.Biosamples), is(new ArrayList<>()));
    }

    @Test
    public void findValidationResultByValidEntityUuidShouldReturnResult() {
        ValidationResult actualValidationResult = validationResultRepository.findByEntityUuid(ENTITY_UUID_1);

        assertThat(actualValidationResult.getSubmissionId(), is(equalTo(SUBMISSION_ID_1)));
    }

    @Test
    public void findValidationResultByInvalidEntityUuid() {
        ValidationResult actualValidationResult = validationResultRepository.findByEntityUuid(ENTITY_UUID_INVALID);

        assertThat(actualValidationResult, nullValue());
    }

    @Test
    public void findBySubmissionIdTest() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ValidationResult> actualValidationResultsPaged =
                validationResultRepository.findBySubmissionId(SUBMISSION_ID_1, pageRequest);

        assertThat(actualValidationResultsPaged.getTotalElements(), is(equalTo(2L)));

        List<ValidationResult> actualValidationResults = actualValidationResultsPaged.getContent();
        assertThat(actualValidationResults.get(0).getEntityUuid(), is(equalTo(ENTITY_UUID_3)));
        assertThat(actualValidationResults.get(1).getEntityUuid(), is(equalTo(ENTITY_UUID_1)));
    }

    @Test
    public void findValidationResultByInvalidSubmissionId() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ValidationResult> actualEmptyValidationResult =
                validationResultRepository.findBySubmissionId(SUBMISSION_ID_INVALID, pageRequest);

        assertThat(actualEmptyValidationResult.getTotalElements(), is(equalTo(0L)));
    }

    @After
    public void tearDown() {
        validationResultRepository.deleteAll();
    }

}