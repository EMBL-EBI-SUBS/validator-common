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

    //---- START of Taxonomy Validator messaging settings ----/
    /**
     * Instantiate a {@link Queue} to validate samples taxonomy entries.
     *
     * @return an instance of a {@link Queue} to validate samples taxonomy entries.
     */
    @Bean
    Queue taxonSampleQueue() {
        return new Queue(Queues.TAXON_SAMPLE_VALIDATION, true);
    }

    /**
     * Create a {@link Binding} between the validation exchange and Taxonomy sample validation queue
     * using the routing key of created samples.
     *
     * @param taxonSampleQueue to validate samples taxonomy entries.
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding}  between the validation exchange and Taxonomy sample validation queue
     * using the routing key of created samples.
     */
    @Bean
    Binding taxonValidationForCreatedSamples(Queue taxonSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(taxonSampleQueue())
                .to(validationExchange)
                .with(RoutingKeys.EVENT_TAXON_SAMPLE_CREATED);
    }

    /**
     * Create a {@link Binding} between the validation exchange and Taxonomy sample validation queue
     * using the routing key of updated samples.
     *
     * @param taxonSampleQueue to validate samples taxonomy entries.
     * @param validationExchange {@link TopicExchange} for validation
     * @return a {@link Binding}  between the validation exchange and Taxonomy sample validation queue
     * using the routing key of updated samples.
     */
    @Bean
    Binding taxonValidationForUpdatedSamples(Queue taxonSampleQueue, TopicExchange validationExchange) {
        return BindingBuilder.bind(taxonSampleQueue())
                .to(validationExchange)
                .with(RoutingKeys.EVENT_TAXON_SAMPLE_UPDATED);
    }
    //---- END of Taxonomy Validator messaging settings ----/

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
