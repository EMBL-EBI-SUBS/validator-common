package uk.ac.ebi.subs.validator.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for the configuration of the validation queues.
 *
 * Created by karoly on 28/04/2017.
 */
@Configuration
public class ValidatorQueueConfig {

    /**
     * Instantiate a {@link Queue} for validate published submissions.
     *
     * @return an instance of a {@link Queue} for validate published submissions.
     */
    @Bean
    Queue submissionSampleValidatorQueue() {
        return new Queue(Queues.SUBMISSION_SAMPLE_VALIDATOR, true);
    }

    /**
     * Create a {@link Binding} between the submission exchange and validation queue using the routing key of created submissions.
     *
     * @param submissionSampleValidatorQueue {@link Queue} for validating sample submissions before submitting them
     * @param submissionExchange {@link TopicExchange} for submissions
     * @return a {@link Binding} between the submission exchange and validation queue using the routing key of created submissions.
     */
    @Bean
    Binding validationForCreatedSubmissionBinding(Queue submissionSampleValidatorQueue, TopicExchange submissionExchange) {
        return BindingBuilder.bind(submissionSampleValidatorQueue).to(submissionExchange)
                .with(RoutingKeys.SUBMISSION_VALIDATOR_SUBMISSION_CREATED);
    }

    /**
     * Create a {@link Binding} between the submission exchange and validation queue using the routing key of updated submissions.
     *
     * @param submissionSampleValidatorQueue {@link Queue} for validating sample submissions before submitting them
     * @param submissionExchange {@link TopicExchange} for submissions
     * @return a {@link Binding} between the submission exchange and validation queue using the routing key of updated submissions.
     */
    @Bean
    Binding validationForUpdatedSubmissionBinding(Queue submissionSampleValidatorQueue, TopicExchange submissionExchange) {
        return BindingBuilder.bind(submissionSampleValidatorQueue).to(submissionExchange)
                .with(RoutingKeys.SUBMISSION_VALIDATOR_SUBMISSION_UPDATED);
    }

    /**
     * Instantiate a {@link Queue} for validate samples related to BioSamples.
     *
     * @return an instance of a {@link Queue} for validate samples related to BioSamples.
     */
    @Bean
    Queue biosamplesSampleQueue() {
        return new Queue(Queues.BIOSAMPLES_SAMPLE_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and BioSamples sample validation queue
     * using the routing key of created samples related to BioSamples.
     *
     * @param biosamplesSampleQueue {@link Queue} for validating BioSamples related samples
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and BioSamples sample validation queue
     * using the routing key of created samples related to BioSamples.
     */
    @Bean
    Binding validationForCreatedBiosamplesSampleBinding(Queue biosamplesSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(biosamplesSampleQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_BIOSAMPLES_SAMPLE_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and BioSamples sample validation queue
     * using the routing key of updated samples related to BioSamples.
     *
     * @param biosamplesSampleQueue {@link Queue} for validating BioSamples related samples
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and BioSamples sample validation queue
     * using the routing key of updated samples related to BioSamples.
     */
    @Bean
    Binding validationForUpdatedBiosamplesSampleBinding(Queue biosamplesSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(biosamplesSampleQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_BIOSAMPLES_SAMPLE_UPDATED);
    }

    //---- START OF ENA Validator messaging settings ----/

    /**
     * Instantiate a {@link Queue} for validate studies related to ENA.
     *
     * @return an instance of a {@link Queue} for validate studies related to ENA.
     */
    @Bean
    Queue enaStudyQueue() {
        return new Queue(Queues.ENA_STUDY_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA study validation queue
     * using the routing key of created studies related to ENA.
     *
     * @param enaStudyQueue {@link Queue} for validating ENA related studies
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA study validation queue
     * using the routing key of created studies related to ENA.
     */
    @Bean
    Binding validationForCreatedENAStudyBinding(Queue enaStudyQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaStudyQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_STUDY_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA study validation queue
     * using the routing key of updated studies related to ENA.
     *
     * @param enaStudyQueue {@link Queue} for validating ENA related studies
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA study validation queue
     * using the routing key of updated studies related to ENA.
     */
    @Bean
    Binding validationForUpdatedENAStudyBinding(Queue enaStudyQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaStudyQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_STUDY_UPDATED);
    }

    /**
     * Instantiate a {@link Queue} for validate samples related to ENA.
     *
     * @return an instance of a {@link Queue} for validate samples related to ENA.
     */
    @Bean
    Queue enaSampleQueue() {
        return new Queue(Queues.ENA_SAMPLE_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA sample validation queue
     * using the routing key of created samples related to ENA.
     *
     * @param enaSampleQueue {@link Queue} for validating ENA related samples
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA sample validation queue
     * using the routing key of created samples related to ENA.
     */
    @Bean
    Binding validationForCreatedENASampleBinding(Queue enaSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaSampleQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_SAMPLE_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA sample validation queue
     * using the routing key of updated samples related to ENA.
     *
     * @param enaSampleQueue {@link Queue} for validating ENA related samples
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA sample validation queue
     * using the routing key of updated samples related to ENA.
     */
    @Bean
    Binding validationForUpdatedENASampleBinding(Queue enaSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaSampleQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_SAMPLE_UPDATED);
    }

    /**
     * Instantiate a {@link Queue} for validate assays related to ENA.
     *
     * @return an instance of a {@link Queue} for validate assays related to ENA.
     */
    @Bean
    Queue enaAssayQueue() {
        return new Queue(Queues.ENA_ASSAY_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA assay validation queue
     * using the routing key of created assays related to ENA.
     *
     * @param enaAssayQueue {@link Queue} for validating ENA related assays
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA assay validation queue
     * using the routing key of created assays related to ENA.
     */
    @Bean
    Binding validationForCreatedENAAssayBinding(Queue enaAssayQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaAssayQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_ASSAY_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA assay validation queue
     * using the routing key of updated assays related to ENA.
     *
     * @param enaAssayQueue {@link Queue} for validating ENA related assays
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA assay validation queue
     * using the routing key of updated assays related to ENA.
     */
    @Bean
    Binding validationForUpdatedENAAssayBinding(Queue enaAssayQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaAssayQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_ASSAY_UPDATED);
    }

    /**
     * Instantiate a {@link Queue} for validate assay data related to ENA.
     *
     * @return an instance of a {@link Queue} for validate assay data related to ENA.
     */
    @Bean
    Queue enaAssayDataQueue() {
        return new Queue(Queues.ENA_ASSAYDATA_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA assay data validation queue
     * using the routing key of created assay data related to ENA.
     *
     * @param enaAssayDataQueue {@link Queue} for validating ENA related assay data
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA assay data validation queue
     * using the routing key of created assay data related to ENA.
     */
    @Bean
    Binding validationForCreatedENAAssayDataBinding(Queue enaAssayDataQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaAssayDataQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_ASSYADATA_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and ENA assay data validation queue
     * using the routing key of updated assay data related to ENA.
     *
     * @param enaAssayDataQueue {@link Queue} for validating ENA related assay data
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and ENA assay data validation queue
     * using the routing key of updated assay data related to ENA.
     */
    @Bean
    Binding validationForUpdatedENAAssayDataBinding(Queue enaAssayDataQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(enaAssayDataQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_ENA_ASSYADATA_UPDATED);
    }

    //---- END OF ENA Validator messaging settings ----/

    /**
     * Instantiate a {@link Queue} for validate samples related to Array Express.
     *
     * @return an instance of a {@link Queue} for validate samples related to Array Express.
     */
    @Bean
    Queue aeSampleQueue() {
        return new Queue(Queues.AE_SAMPLE_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and Array Express sample validation queue
     * using the routing key of created samples related to Array Express.
     *
     * @param aeSampleQueue {@link Queue} for validating Array Express related samples
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and Array Express sample validation queue
     * using the routing key of created samples related to Array Express.
     */
    @Bean
    Binding validationForCreatedAESampleBinding(Queue aeSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(aeSampleQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_AE_SAMPLE_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and Array Express sample validation queue
     * using the routing key of updated samples related to Array Express.
     *
     * @param aeSampleQueue {@link Queue} for validating Array Express related samples
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and Array Express sample validation queue
     * using the routing key of updated samples related to Array Express.
     */
    @Bean
    Binding validationForUpdatedAESampleBinding(Queue aeSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(aeSampleQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_AE_SAMPLE_UPDATED);
    }

    /**
     * Instantiate a {@link Queue} for publish validation results.
     *
     * @return an instance of a {@link Queue} for publish validation results.
     */
    @Bean
    Queue validationResultQueue() {
        return new Queue(Queues.VALIDATION_RESULT, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and validation result queue
     * using the routing key of successful validation.
     *
     * @param validationResultQueue {@link Queue} for validation results
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and validation result queue
     * using the routing key of successful validation.
     */
    @Bean
    Binding validationResultSuccessBinding(Queue validationResultQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(validationResultQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_VALIDATION_SUCCESS);
    }

    /**
     * Create a {@link Binding} between the validation exchange and validation result queue
     * using the routing key of erred validation.
     *
     * @param validationResultQueue {@link Queue} for validation results
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and validation result queue
     * using the routing key of erred validation.
     */
    @Bean
    Binding validationResultErrorBinding(Queue validationResultQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(validationResultQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_VALIDATION_ERROR);
    }

    /**
     * Instantiate a {@link Queue} for publish events related to the validation result document.
     *
     * @return an instance of a {@link Queue} for publish events related to the validation result document.
     */
    @Bean
    Queue validationResultDocumentQueue() {
        return new Queue(Queues.VALIDATION_RESULT_DOCUMENT_UPDATE, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and the validation result document queue
     * using the routing key of validation result document updated.
     *
     * @param validationResultDocumentQueue {@link Queue} for validation result document events
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding} between the validation exchange and the validation result document queue
     * using the routing key of validation result document updated.
     */
    @Bean
    Binding validationResultDocumentUpdatedBinding(Queue validationResultDocumentQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(validationResultDocumentQueue).to(validationExchange)
                .with(RoutingKeys.EVENT_VALIDATION_RESULT_DOCUMENT_UPDATED);
    }
}
