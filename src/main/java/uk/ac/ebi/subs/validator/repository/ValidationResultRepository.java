package uk.ac.ebi.subs.validator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.subs.validator.data.ValidationResult;

import java.util.List;

/**
 * Mongo repository REST resource for {@code ValidationResult}.
 */
@RepositoryRestResource
public interface ValidationResultRepository extends MongoRepository<ValidationResult, String> {

    @RestResource(exported = true, path = "by-entity", rel = "by-entity")
    ValidationResult findByEntityUuid(@Param("entityUuid") String entityUuid);

    @RestResource(exported = true, path = "by-submission", rel = "by-submission")
    Page<ValidationResult> findBySubmissionId(@Param("submissionId") String submissionId, Pageable pageable);

    @RestResource(exported = true, path = "all-by-submission", rel = "all-by-submission")
    List<ValidationResult> findAllBySubmissionId(@Param("submissionId") String submissionId);

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
}
