package com.kpoy.housecams.configuration;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Slf4j
@Configuration
@EnableR2dbcRepositories
public class ApplicationConfiguration extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        log.debug("returning new connectionFactory");

        // Map<H2ConnectionOption, String> h2properties = new HashMap<>();
        // h2properties.put(H2ConnectionOption.DB_CLOSE_DELAY, "-1");
        //    return new H2ConnectionFactory( // .inMemory("housecams", "sa", "", h2properties);
        //            H2ConnectionConfiguration.builder()
        //                    .file("./housecamsdb")
        //                    .username("sa")
        //                    .build());
        // }

        // return ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        return ConnectionFactoryBuilder.withUrl("r2dbc:h2:mem:///housecams?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE").build();
    }
}

