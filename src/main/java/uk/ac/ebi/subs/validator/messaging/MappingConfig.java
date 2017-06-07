package uk.ac.ebi.subs.validator.messaging;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Contains the configuration for mapping JSON messages.
 *
 * Created by karoly on 28/04/2017.
 */
@Component
@Configuration
public class MappingConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
