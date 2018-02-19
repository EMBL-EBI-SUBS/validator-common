package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Assay;
import uk.ac.ebi.subs.data.submittable.Sample;
import uk.ac.ebi.subs.data.submittable.Study;
import uk.ac.ebi.subs.validator.model.Submittable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * This is a Data Transfer Object transferring {@link Assay} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Assay} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class AssayValidationMessageEnvelope extends ValidationMessageEnvelope<Assay> {
    private Submittable<Study> study;
    private List<Submittable<Sample>> sampleList = new ArrayList();

    public AssayValidationMessageEnvelope(String validationResultUUID,
                                          int validationResultVersion,
                                          Assay entityToValidate,
                                          String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate,submissionId);
    }

    public AssayValidationMessageEnvelope() {
    }

    public Submittable<Study> getStudy() {
        return study;
    }

    public void setStudy(Submittable<Study> study) {
        this.study = study;
    }

    public List<Submittable<Sample>> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<Submittable<Sample>> sampleList) {
        this.sampleList = sampleList;
    }

    @Override
    public Stream<uk.ac.ebi.subs.validator.model.Submittable> allSubmissionItemsStream() {
        final Stream<Submittable> submittableStream = super.allSubmissionItemsStream();
        return Stream.of(
                submittableStream,
                Stream.of(this.study),
                sampleList.stream()
        ).flatMap(i -> i);
    }
}
