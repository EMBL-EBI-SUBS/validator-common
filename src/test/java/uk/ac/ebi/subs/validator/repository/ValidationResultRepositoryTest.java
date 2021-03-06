package uk.ac.ebi.subs.validator.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.subs.validator.data.SingleValidationResult;
import uk.ac.ebi.subs.validator.data.ValidationResult;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ValidationResultRepository.class, ValidatorResultRepositoryCustom.class})
@EnableAutoConfiguration
public class ValidationResultRepositoryTest {

    private static final String SUBMISSION_ID_1 = "123";
    private static final String SUBMISSION_ID_2 = "456";
    private static final String SUBMISSION_ID_INVALID = "invalid submission id";
    private static final String ENTITY_UUID_1 = "44566";
    private static final String ENTITY_UUID_2 = "98876";
    private static final String ENTITY_UUID_3 = "11223";
    private static final String ENTITY_UUID_INVALID = "invalid entity uuid";
    private static final String SAMPLES_DATA_TYPE = "samples";
    private static final String SEQUENCING_RUN_DATA_TYPE = "sequencingRuns";

    @Autowired
    ValidationResultRepository validationResultRepository;

    @Autowired
    ValidatorResultRepositoryCustom validatorResultRepositoryCustom;

    private ValidationResult validationResult;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void buildUp() {
        Map<ValidationAuthor, List<SingleValidationResult>> expectedResults = new HashMap<>();
        expectedResults.put(ValidationAuthor.Biosamples, Collections.singletonList(generateSingleValidationResult(SingleValidationResultStatus.Error)));
        expectedResults.put(ValidationAuthor.Taxonomy, Collections.singletonList(generateSingleValidationResult(SingleValidationResultStatus.Pass)));
        expectedResults.put(ValidationAuthor.Ena, Collections.singletonList(generateSingleValidationResult(SingleValidationResultStatus.Warning)));

        // First
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setVersion(1);
        validationResult.setSubmissionId(SUBMISSION_ID_1);
        validationResult.setEntityUuid(ENTITY_UUID_1);
        validationResult.setDataTypeId(SAMPLES_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);

        validationResultRepository.insert(validationResult);

        // Second
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setSubmissionId(SUBMISSION_ID_2);
        validationResult.setEntityUuid(ENTITY_UUID_2);
        validationResult.setDataTypeId(SEQUENCING_RUN_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);

        validationResultRepository.insert(validationResult);

        // Third
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setSubmissionId(SUBMISSION_ID_1);
        validationResult.setEntityUuid(ENTITY_UUID_3);
        validationResult.setDataTypeId(SEQUENCING_RUN_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);

        validationResultRepository.insert(validationResult);

        // Forth
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setSubmissionId(SUBMISSION_ID_1);
        validationResult.setEntityUuid(ENTITY_UUID_2);
        validationResult.setDataTypeId(SAMPLES_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);

        validationResultRepository.insert(validationResult);

        // Fifth
        expectedResults.put(ValidationAuthor.Biosamples, Collections.singletonList(generateSingleValidationResult(SingleValidationResultStatus.Pass)));

        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setSubmissionId(SUBMISSION_ID_1);
        validationResult.setEntityUuid(ENTITY_UUID_2);
        validationResult.setDataTypeId(SAMPLES_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);

        validationResultRepository.insert(validationResult);

        // Sixth
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setSubmissionId(SUBMISSION_ID_2);
        validationResult.setEntityUuid(ENTITY_UUID_2);
        validationResult.setDataTypeId(SEQUENCING_RUN_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Pending);

        validationResultRepository.insert(validationResult);

        // Seventh
        validationResult = new ValidationResult();
        validationResult.setUuid(UUID.randomUUID().toString());
        validationResult.setExpectedResults(expectedResults);
        validationResult.setSubmissionId(SUBMISSION_ID_2);
        validationResult.setEntityUuid(ENTITY_UUID_2);
        validationResult.setDataTypeId(SAMPLES_DATA_TYPE);
        validationResult.setValidationStatus(GlobalValidationStatus.Pending);

        validationResultRepository.insert(validationResult);
    }

    @Test
    public void persistValidationResultTest() {
        ValidationResult retrievedResult = validationResultRepository.findOne(validationResult.getUuid());
        System.out.println(retrievedResult);

        assertThat(retrievedResult.getExpectedResults().get(ValidationAuthor.Ena),
                is(Collections.singletonList(generateSingleValidationResult(SingleValidationResultStatus.Warning))));
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
        PageRequest pageRequest =  new PageRequest(0, 10);
        Page<ValidationResult> actualValidationResultsPaged =
                validationResultRepository.findBySubmissionId(SUBMISSION_ID_1, pageRequest);

        assertThat(actualValidationResultsPaged.getTotalElements(), is(equalTo(4L)));

        List<ValidationResult> actualValidationResults = actualValidationResultsPaged.getContent();
        final List<String> uuids =
                actualValidationResults.stream().map(ValidationResult::getEntityUuid).collect(Collectors.toList());
        assertThat(uuids, hasItem(ENTITY_UUID_3));
        assertThat(uuids, hasItem(ENTITY_UUID_1));
    }

    @Test
    public void findValidationResultByInvalidSubmissionId() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ValidationResult> actualEmptyValidationResult =
                validationResultRepository.findBySubmissionId(SUBMISSION_ID_INVALID, pageRequest);

        assertThat(actualEmptyValidationResult.getTotalElements(), is(equalTo(0L)));
    }

    @Test
    public void findAllBySubmissionIdTest() {
        List<ValidationResult> actualValidationResults =
                validationResultRepository.findAllBySubmissionId(SUBMISSION_ID_1);

        assertThat(actualValidationResults.size(), is(equalTo(4)));

        final List<String> uuids =
                actualValidationResults.stream().map(ValidationResult::getEntityUuid).collect(Collectors.toList());
        assertThat(uuids, hasItem(ENTITY_UUID_3));
        assertThat(uuids, hasItem(ENTITY_UUID_1));
    }

    @Test
    public void findAllValidationResultByInvalidSubmissionId() {
        List<ValidationResult> actualEmptyValidationResults =
                validationResultRepository.findAllBySubmissionId(SUBMISSION_ID_INVALID);

        assertThat(actualEmptyValidationResults.size(), is(equalTo(0)));
    }

    @Test
    public void findValidationResultsBySubmissionIdAndDataTypeId() {
        Stream<ValidationResult> streamOfValidationResults =
                validationResultRepository.findBySubmissionIdAndDataTypeId(SUBMISSION_ID_1, SEQUENCING_RUN_DATA_TYPE);

        List<ValidationResult> validationResults = streamOfValidationResults.collect(Collectors.toList());

        assertThat(validationResults.size(), is(equalTo(1)));
        assertThat(validationResults.get(0).getDataTypeId(), is(equalTo(SEQUENCING_RUN_DATA_TYPE)));
        assertThat(validationResults.get(0).getSubmissionId(), is(equalTo(SUBMISSION_ID_1)));
    }

    @Test
    public void findValidationResultsBySubmissionIdAndDataTypeIdAndHasError() {
        Page<ValidationResult> pagedValidationResults =
                validationResultRepository.findBySubmissionIdAndDataTypeIdAndHasError(
                        SUBMISSION_ID_1, SAMPLES_DATA_TYPE, true, new PageRequest(0, 50));

        assertThat(pagedValidationResults.getTotalElements(), is(equalTo(2L)));
        pagedValidationResults.forEach( validationResultFromPagedResult -> {
            assertThat(validationResultFromPagedResult.getDataTypeId(), is(equalTo(SAMPLES_DATA_TYPE)));
            assertThat(validationResultFromPagedResult.getSubmissionId(), is(equalTo(SUBMISSION_ID_1)));
            assertThat(validationResultFromPagedResult.hasError(), is(equalTo(true)));
        });
    }

    @Test
    public void findValidationResultsBySubmissionIdAndDataTypeIdAndHasWarning() {
        Page<ValidationResult> pagedValidationResults =
                validationResultRepository.findBySubmissionIdAndDataTypeIdAndHasWarning(
                        SUBMISSION_ID_2, SEQUENCING_RUN_DATA_TYPE, true, new PageRequest(0, 50));

        assertThat(pagedValidationResults.getTotalElements(), is(equalTo(1L)));
        pagedValidationResults.forEach( validationResultFromPagedResult -> {
            assertThat(validationResultFromPagedResult.getDataTypeId(), is(equalTo(SEQUENCING_RUN_DATA_TYPE)));
            assertThat(validationResultFromPagedResult.getSubmissionId(), is(equalTo(SUBMISSION_ID_2)));
            assertThat(validationResultFromPagedResult.hasWarning(), is(equalTo(true)));
        });
    }

    @Test
    public void getValidationIssuesByDataTypeID() {
        Map<String, Integer> validationIssuesByDataType =
                validatorResultRepositoryCustom.validationIssuesPerDataTypeId(SUBMISSION_ID_1);

        assertThat(validationIssuesByDataType, is(notNullValue()));
        assertThat(validationIssuesByDataType.size(), is(equalTo(2)));
        assertThat(validationIssuesByDataType.get("samples"), is(equalTo(2)));
        assertThat(validationIssuesByDataType.get("sequencingRuns"), is(equalTo(1)));
    }

    @Test
    public void given3ValidationResultForASubmissionWith2Pending_thenQueryReturnsThe2PendingOnes() {
        long pendingValidationResultCount =
                validationResultRepository.countBySubmissionIdAndValidationStatusIs(
                        SUBMISSION_ID_2, GlobalValidationStatus.Pending);

        assertThat(pendingValidationResultCount, is(equalTo(2L)));
    }

    @After
    public void tearDown() {
        validationResultRepository.deleteAll();
    }

    private SingleValidationResult generateSingleValidationResult(SingleValidationResultStatus singleValidationResultStatus) {
        SingleValidationResult singleValidationResult = new SingleValidationResult();
        singleValidationResult.setValidationAuthor(ValidationAuthor.Core);
        singleValidationResult.setValidationStatus(singleValidationResultStatus);

        return singleValidationResult;
    }
}