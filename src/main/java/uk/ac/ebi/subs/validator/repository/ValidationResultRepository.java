package uk.ac.ebi.subs.validator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.subs.validator.data.ValidationResult;
import uk.ac.ebi.subs.validator.data.structures.GlobalValidationStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Mongo repository REST resource for {@code ValidationResult}.
 */
@RepositoryRestResource(excerptProjection = ValidationResultStatus.class)
public interface ValidationResultRepository extends MongoRepository<ValidationResult, String> {

    @RestResource(exported = true, path = "by-entity", rel = "by-entity")
    ValidationResult findByEntityUuid(@Param("entityUuid") String entityUuid);

    @RestResource(exported = true, path = "by-submission", rel = "by-submission")
    Page<ValidationResult> findBySubmissionId(@Param("submissionId") String submissionId, Pageable pageable);

    @RestResource(exported = false)
    List<ValidationResult> findAllBySubmissionId(@Param("submissionId") String submissionId);

    @RestResource(exported = false)
    Stream<ValidationResult> findBySubmissionIdAndDataTypeId(@Param("submissionId") String submissionId, @Param("dataTypeId") String dataTypeId);

    @RestResource(exported = false)
    long countBySubmissionIdAndValidationStatusIs(
            @Param("submissionId") String submissionId, @Param("validationStatus") GlobalValidationStatus validationStatus);

    @RestResource(exported = false)
    Page<ValidationResult> findBySubmissionIdAndDataTypeIdAndHasError(
            @Param("submissionId") String submissionId, @Param("dataTypeId") String dataTypeId,
            @Param("hasError") boolean hasError, Pageable pageable);

    @RestResource(exported = false)
    Page<ValidationResult> findBySubmissionIdAndDataTypeIdAndHasWarning(
            @Param("submissionId") String submissionId, @Param("dataTypeId") String dataTypeId,
            @Param("hasWarning") boolean hasWarning, Pageable pageable);

    //TODO karoly: need to add access control (when implementing AAP) to these methods
    // exported as GET /validationresults/:id
    @Override
    @RestResource(exported = true)
    ValidationResult findOne(String id);

    // exported as GET /validationresults
    @Override
    @RestResource(exported = false)
    Page<ValidationResult> findAll(Pageable pageable);

    // controls PUT /validationresults and PATCH /validationresults/:id
    @Override
    @RestResource(exported = false)
    <S extends ValidationResult> S save(S s);

    // controls POST /validationresults
    @Override
    @RestResource(exported = false)
    <S extends ValidationResult> S insert(S s);

    // exported as DELETE /validationresults/:id
    @Override
    @RestResource(exported = false)
    void delete(ValidationResult t);

    @RestResource(exported = false)
    void deleteAllBySubmissionId(String submissionId);
}
