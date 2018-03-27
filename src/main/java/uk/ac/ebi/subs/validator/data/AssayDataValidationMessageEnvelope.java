package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Sample;
import uk.ac.ebi.subs.validator.model.Submittable;

import uk.ac.ebi.subs.data.submittable.Assay;
import uk.ac.ebi.subs.data.submittable.AssayData;

import java.util.stream.Stream;

/**
 * This is a Data Transfer Object transferring {@link AssayData} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link AssayData} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class AssayDataValidationMessageEnvelope extends ValidationMessageEnvelope<AssayData> {

    public AssayDataValidationMessageEnvelope(String validationResultUUID,
                                              int validationResultVersion,
                                              AssayData entityToValidate,
                                              String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate,submissionId);
    }

    public AssayDataValidationMessageEnvelope() {
    }

    private Submittable<Assay> assay;
    private Submittable<Sample> sample;

    public Submittable<Assay> getAssay() {
        return assay;
    }

    public void setAssay(Submittable<Assay> assay) {
        this.assay = assay;
    }

    public Submittable<Sample> getSample() {
        return sample;
    }

    public void setSample(Submittable<Sample> sample) {
        this.sample = sample;
    }

    @Override
    public Stream<Submittable> allSubmissionItemsStream() {
        return Stream.of(
                super.allSubmissionItemsStream(),
                Stream.of(this.assay),
                Stream.of(this.sample)
        ).flatMap(i -> i);
    }
}
