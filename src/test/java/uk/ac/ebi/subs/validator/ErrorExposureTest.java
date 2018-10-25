package uk.ac.ebi.subs.validator;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.subs.validator.data.SingleValidationResult;
import uk.ac.ebi.subs.validator.data.ValidationResult;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;
import uk.ac.ebi.subs.validator.data.structures.SingleValidationResultStatus;
import uk.ac.ebi.subs.validator.data.structures.ValidationAuthor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;


public class ErrorExposureTest {

    ValidationResult validationResult;

    @Test
    public void overallValidationOutcomeByAuthor_ShouldBe_Empty() {
        validationResult.setValidationStatus(GlobalValidationStatus.Pending);
        assertThat(validationResult.getOverallValidationOutcomeByAuthor(), is(anEmptyMap()));
    }

    @Test
    public void errorMessages_ShouldBe_Empty() {
        validationResult.setValidationStatus(GlobalValidationStatus.Pending);
        assertThat(validationResult.getErrorMessages(), is(anEmptyMap()));
    }

    @Test
    public void overallValidationOutcomeByAuthor_ShouldHave_ThreeEntries() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getOverallValidationOutcomeByAuthor(), is(aMapWithSize(4)));
    }

    @Test
    public void errorMessages_ShouldHave_ThreeEntries() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getErrorMessages(), is(aMapWithSize(3)));
    }

    @Test
    public void overallValidationOutcomeByBioSamples_ShouldBe_Warning() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getOverallValidationOutcomeByAuthor().get(ValidationAuthor.Biosamples), is(SingleValidationResultStatus.Warning.toString()));
    }

    @Test
    public void overallValidationOutcomeByCore_ShouldBe_Pass() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getOverallValidationOutcomeByAuthor().get(ValidationAuthor.Core), is(SingleValidationResultStatus.Pass.toString()));
    }

    @Test
    public void overallValidationOutcomeByEna_ShouldBe_Error() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getOverallValidationOutcomeByAuthor().get(ValidationAuthor.Ena), is(SingleValidationResultStatus.Error.toString()));
    }

    @Test
    public void overallValidationOutcomeByTaxonomy_ShouldBe_Error() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getOverallValidationOutcomeByAuthor().get(ValidationAuthor.Taxonomy), is(SingleValidationResultStatus.Error.toString()));
    }

    @Test
    public void errorMessagesByBioSamples_ShouldHave_OneMessage() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getErrorMessages().get(ValidationAuthor.Biosamples), hasSize(1));
    }

    @Test
    public void errorMessagesByCore_Should_notBePresent() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getErrorMessages().containsKey(ValidationAuthor.Core), is(false));
    }

    @Test
    public void errorMessagesByEna_ShouldHave_TwoMessages() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getErrorMessages().get(ValidationAuthor.Ena), hasSize(2));
    }

    @Test
    public void errorMessagesByTaxonomy_ShouldHave_TwoMessages() {
        validationResult.setValidationStatus(GlobalValidationStatus.Complete);
        assertThat(validationResult.getErrorMessages().get(ValidationAuthor.Taxonomy), hasSize(2));
    }

    @Test(expected = Exception.class)
    public void overallValidationOutcomeByAuthor_ShouldBe_Unmodifiable() {
        validationResult.getOverallValidationOutcomeByAuthor().put(ValidationAuthor.Biosamples, "");
    }

    @Test(expected = Exception.class)
    public void errorMessages_ShouldBe_Unmodifiable() {
        validationResult.getErrorMessages().put(ValidationAuthor.Biosamples, new ArrayList<>());
    }

    @Before
    public void setUp() {
        validationResult = new ValidationResult();
        Map<ValidationAuthor, List<SingleValidationResult>> expectedResults = new HashMap<>();
        // Biosamples
        expectedResults.put(ValidationAuthor.Biosamples, Arrays.asList(createSingleValidationResult(SingleValidationResultStatus.Warning, "Example warning message."),
                createSingleValidationResult(SingleValidationResultStatus.Pass, null)));
        // Core
        expectedResults.put(ValidationAuthor.Core, Arrays.asList(createSingleValidationResult(SingleValidationResultStatus.Pass, null)));
        // Ena
        expectedResults.put(ValidationAuthor.Ena, Arrays.asList(createSingleValidationResult(SingleValidationResultStatus.Error, "Example error message."),
                createSingleValidationResult(SingleValidationResultStatus.Error, "Example error message 2."),
                createSingleValidationResult(SingleValidationResultStatus.Pass, null)));
        // Taxonomy
        expectedResults.put(ValidationAuthor.Taxonomy, Arrays.asList(createSingleValidationResult(SingleValidationResultStatus.Warning, "Example warning message."),
                createSingleValidationResult(SingleValidationResultStatus.Error, "Example error message.")));
        validationResult.setExpectedResults(expectedResults);
    }

    private SingleValidationResult createSingleValidationResult(SingleValidationResultStatus validationStatus, String message) {
        SingleValidationResult svr = new SingleValidationResult();
        svr.setValidationStatus(validationStatus);
        svr.setMessage(message);
        return svr;
    }
}
