package uk.ac.ebi.subs.validator.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Service
@RequiredArgsConstructor
public class ValidatorResultRepositoryCustom {

    @NonNull
    private MongoTemplate mongoTemplate;

    public Map<String, Integer> validationIssuesPerDataTypeId(String submissionID) {
        MatchOperation filterSubmissionsWithErrors = match(
                new Criteria().andOperator(
                        Criteria.where("submissionId").is(submissionID)
                        .and("hasError").is(true)
                )
        );
        GroupOperation groupByDataTypeId = group("dataTypeId").count().as("count");

        Aggregation aggregation
                = Aggregation.newAggregation(filterSubmissionsWithErrors, groupByDataTypeId);

        List<HashMap> queryResult =
                mongoTemplate.aggregate(aggregation, "validationResult", HashMap.class)
                .getMappedResults();

        Map<String, Integer> validationIssuesByDataTypeID = queryResult.stream()
                .collect(Collectors.toMap(result ->
                        (String) result.get("_id"), s -> (Integer) s.get("count")
                )
        );

        return validationIssuesByDataTypeID;
    }
}
