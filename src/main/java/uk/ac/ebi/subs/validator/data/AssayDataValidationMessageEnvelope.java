package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Assay;
import uk.ac.ebi.subs.data.submittable.AssayData;

/**
 * This is a Data Transfer Object transferring {@link AssayData} data from the {@code validator-coordinator} service
 * to a specific {@code Validator}.
 * A container object holding the UUID (MongoDB ID) and the {@link AssayData} entity to validate.
 *
 * Created by karoly on 06/10/2017.
 */
public class AssayDataValidationMessageEnvelope extends ValidationMessageEnvelope<AssayData> {
    private Assay assay;

    public Assay getAssay() {
        return assay;
    }

    public void setAssay(Assay assay) {
        this.assay = assay;
    }
}
