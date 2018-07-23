package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Protocol;
import uk.ac.ebi.subs.data.submittable.Sample;
import uk.ac.ebi.subs.validator.model.Submittable;

import uk.ac.ebi.subs.data.submittable.Assay;
import uk.ac.ebi.subs.data.submittable.AssayData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    private Collection<Submittable<Assay>> assays = new ArrayList<>();

    public Collection<Submittable<Assay>> getAssays() {
        return assays;
    }

    public void setAssays(Collection<Submittable<Assay>> assays) {
        this.assays = assays;
    }

    private List<Protocol> protocols = new ArrayList<>();


    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }


    @Override
    public Stream<Submittable> allSubmissionItemsStream() {
        return Stream.concat(
                super.allSubmissionItemsStream(),
                assays.stream()
        );
    }
}
