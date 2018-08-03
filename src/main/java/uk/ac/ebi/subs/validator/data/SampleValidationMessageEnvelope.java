package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.component.StudyDataType;
import uk.ac.ebi.subs.data.submittable.Sample;
import uk.ac.ebi.subs.validator.model.Submittable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This is a Data Transfer Object transferring {@link Sample} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Sample} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 *
 * Sample may contain relationships that need to be included in the ValidationMessageEnvelope
 */
public class SampleValidationMessageEnvelope extends ValidationMessageEnvelope<Sample> {
    private List<Submittable<Sample>> sampleList = new ArrayList();
    private StudyDataType studyDataType = null;

    public SampleValidationMessageEnvelope() {
    }

    public SampleValidationMessageEnvelope(String validationResultUUID,
                                           int validationResultVersion,
                                           Sample entityToValidate,
                                           String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public List<Submittable<Sample>> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<Submittable<Sample>> sampleList) {
        this.sampleList = sampleList;
    }

    public StudyDataType getStudyDataType(){return studyDataType;}

    public void setStudyDataType(StudyDataType studyDataType){
        this.studyDataType = studyDataType;
    }

    @Override
    public Stream<Submittable> allSubmissionItemsStream() {
        return Stream.of(
                super.allSubmissionItemsStream(),
                sampleList.stream()
        ).flatMap(i -> i);
    }
}
