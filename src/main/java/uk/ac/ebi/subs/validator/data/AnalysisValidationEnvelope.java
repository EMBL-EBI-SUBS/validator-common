package uk.ac.ebi.subs.validator.data;

import uk.ac.ebi.subs.data.submittable.Analysis;
import uk.ac.ebi.subs.data.submittable.Assay;
import uk.ac.ebi.subs.data.submittable.AssayData;
import uk.ac.ebi.subs.data.submittable.Sample;
import uk.ac.ebi.subs.data.submittable.Study;
import uk.ac.ebi.subs.validator.model.Submittable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AnalysisValidationEnvelope extends ValidationMessageEnvelope<Analysis> {

    public AnalysisValidationEnvelope(String validationResultUUID,
                                      int validationResultVersion,
                                      Analysis entityToValidate,
                                      String submissionId) {
        super(validationResultUUID, validationResultVersion, entityToValidate, submissionId);
    }

    public AnalysisValidationEnvelope() {
    }


    private List<Submittable<Analysis>> analyses = new ArrayList<>();
    private List<Submittable<Study>> studies = new ArrayList<>();
    private List<Submittable<Sample>> samples = new ArrayList<>();
    private List<Submittable<Assay>> assays = new ArrayList<>();
    private List<Submittable<AssayData>> assayData = new ArrayList<>();


    @Override
    public Stream<Submittable> allSubmissionItemsStream() {
        return Stream.of(
                super.allSubmissionItemsStream(),
                analyses.stream(),
                studies.stream(),
                samples.stream(),
                assays.stream(),
                assayData.stream()
        ).flatMap(i -> i);
    }

    public List<Submittable<Analysis>> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Submittable<Analysis>> analyses) {
        this.analyses = analyses;
    }

    public List<Submittable<Study>> getStudies() {
        return studies;
    }

    public void setStudies(List<Submittable<Study>> studies) {
        this.studies = studies;
    }

    public List<Submittable<Sample>> getSamples() {
        return samples;
    }

    public void setSamples(List<Submittable<Sample>> samples) {
        this.samples = samples;
    }

    public List<Submittable<Assay>> getAssays() {
        return assays;
    }

    public void setAssays(List<Submittable<Assay>> assays) {
        this.assays = assays;
    }

    public List<Submittable<AssayData>> getAssayData() {
        return assayData;
    }

    public void setAssayData(List<Submittable<AssayData>> assayData) {
        this.assayData = assayData;
    }
}