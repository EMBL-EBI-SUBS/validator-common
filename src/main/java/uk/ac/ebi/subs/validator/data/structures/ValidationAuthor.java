package uk.ac.ebi.subs.validator.data.structures;

/**
 * Controlled list of validation authors.
 */
public enum ValidationAuthor {
    Core,
    Taxonomy,
    Ena, // FIXME - This may need further details like checklist
    Eva,
    Biosamples,
    BioStudies,
    ArrayExpress,
    Metabolights,
    Pride,
    FileReference,
    JsonSchema
}
