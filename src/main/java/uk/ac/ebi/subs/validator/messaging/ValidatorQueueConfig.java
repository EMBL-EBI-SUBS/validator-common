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
}
