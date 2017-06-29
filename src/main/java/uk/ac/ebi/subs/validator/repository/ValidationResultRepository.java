package uk.ac.ebi.subs.validator.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.subs.validator.data.ValidationResult;

/**
 * Mongo repository REST resource for {@code ValidationResult}.
 */
@RepositoryRestResource
public interface ValidationResultRepository extends MongoRepository<ValidationResult, String> {

    @RestResource(exported = true, path = "by-submissionIdAndEntityUuid", rel = "by-submissionIdAndEntityUuid")
    ValidationResult findBySubmissionIdAndEntityUuid(@Param("submissionId") String submissionId,
                                                     @Param("entityUuid") String entityUuid);

    @RestResource(exported = true, path = "by-entityUuid", rel = "by-entityUuid")
    ValidationResult findByEntityUuid(@Param("entityUuid") String entityUuid);

    @RestResource(exported = true, path = "by-submission", rel = "by-submission")
    Page<ValidationResult> findBySubmissionId(@Param("submissionId") String submissionId, Pageable pageable);
}
