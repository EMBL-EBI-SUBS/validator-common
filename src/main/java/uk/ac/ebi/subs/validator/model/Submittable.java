package uk.ac.ebi.subs.validator.model;

import uk.ac.ebi.subs.data.component.Attribute;
import uk.ac.ebi.subs.data.component.Attributes;
import uk.ac.ebi.subs.data.component.Team;
import uk.ac.ebi.subs.data.submittable.BaseSubmittable;


import java.util.Collection;
import java.util.Map;

public class Submittable<T extends BaseSubmittable> implements uk.ac.ebi.subs.data.submittable.Submittable, Attributes {
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

    @Override
    public String getId() {
        return baseSubmittable.getId();
    }

    @Override
    public void setId(String id) {
        baseSubmittable.setId(id);
    }

    @Override
    public String getAccession() {
        return baseSubmittable.getAccession();
    }

    @Override
    public void setAccession(String accession) {
        baseSubmittable.setAccession(accession);
    }

    @Override
    public String getAlias() {
        return baseSubmittable.getAlias();
    }

    @Override
    public void setAlias(String alias) {
        baseSubmittable.setAlias(alias);
    }

    @Override
    public Team getTeam() {
        return baseSubmittable.getTeam();
    }

    @Override
    public void setTeam(Team team) {
        baseSubmittable.setTeam(team);
    }

    @Override
    public String getTitle() {
        return baseSubmittable.getTitle();
    }

    @Override
    public void setTitle(String title) {
        baseSubmittable.setTitle(title);
    }

    @Override
    public String getDescription() {
        return baseSubmittable.getDescription();
    }

    @Override
    public void setDescription(String description) {
        baseSubmittable.setDescription(description);
    }

    @Override
    public Map<String, Collection<Attribute>> getAttributes() {
        return baseSubmittable.getAttributes();
    }

    @Override
    public void setAttributes(Map<String, Collection<Attribute>> attributes) {
        baseSubmittable.setAttributes(attributes);
    }

    @Override
    public boolean isAccessioned() {
        return baseSubmittable.isAccessioned();
    }

    @Override
    public void addAttribute(String name, Attribute attribute) {
        baseSubmittable.addAttribute(name,attribute);
    }
}
