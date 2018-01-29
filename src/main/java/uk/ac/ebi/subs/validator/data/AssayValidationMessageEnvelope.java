package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Assay;
import uk.ac.ebi.subs.data.submittable.Sample;
import uk.ac.ebi.subs.data.submittable.Study;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Data Transfer Object transferring {@link Assay} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link Assay} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class AssayValidationMessageEnvelope extends ValidationMessageEnvelope<Assay> {
    private Study study;
    private List<Sample> sampleList = new ArrayList();

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public List<Sample> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<Sample> sampleList) {
        this.sampleList = sampleList;
    }
}
