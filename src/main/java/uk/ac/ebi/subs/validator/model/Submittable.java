package uk.ac.ebi.subs.validator.model;

import uk.ac.ebi.subs.data.submittable.BaseSubmittable;

public class Submittable<T extends BaseSubmittable> {
    T baseSubmittable;
    String submissionId;

    public Submittable(T baseSubmittable, String submissionId) {
        this.baseSubmittable = baseSubmittable;
        this.submissionId = submissionId;
    }

    public Submittable() {
    }


    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public T getBaseSubmittable() {
        return baseSubmittable;
    }

    public void setBaseSubmittable(T baseSubmittable) {
        this.baseSubmittable = baseSubmittable;
    }
}
