package uk.ac.ebi.subs.validator.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Contains the exchange configuration for validation.
 *
 * Created by karoly on 28/04/2017.
 */
@Configuration
public class ValidationExchangeConfig {

    /**
     * Instantiate a {@link TopicExchange} for validation.
     *
     * @return an instance of {@link TopicExchange} for validation.
     */
    @Bean
    TopicExchange validationExchange() {
        return new TopicExchange(Exchanges.VALIDATION);
    }

    /**
     * Instantiate a {@link TopicExchange} for USI submissions.
     *
     * @return an instance of {@link TopicExchange} for USI submissions.
     */
    @Bean
    TopicExchange submissionExchange() {
        return new TopicExchange(Exchanges.SUBMISSIONS);
    }
}
