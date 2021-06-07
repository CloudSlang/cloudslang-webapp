/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web;

/**
 * Created with IntelliJ IDEA.
 * User: kravtsov
 * Date: 3/1/15
 * Time: 1:09 PM
 */

import io.cloudslang.lang.api.Slang;
import io.cloudslang.lang.api.SlangImpl;
import io.cloudslang.lang.api.configuration.SlangSpringConfiguration;
import io.cloudslang.lang.commons.services.api.UserConfigurationService;
import io.cloudslang.lang.commons.services.impl.UserConfigurationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories("io.cloudslang.web.repositories")
@EntityScan("io.cloudslang.web.client")
@Import(SlangSpringConfiguration.class)
@ImportResource("spring/slangWebappContext.xml")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ScoreWebApplication {

    public static void main(String[] args) {
        loadUserProperties();
        SpringApplication.run(ScoreWebApplication.class, args);
    }

    @Bean
    public Slang getSlang() {
        return new SlangImpl();
    }

    private static void loadUserProperties() {
        try {
            UserConfigurationService userConfigurationService = new UserConfigurationServiceImpl();
            userConfigurationService.loadUserProperties();
        } catch (Exception ex) {
            System.out.println("Error occurred while loading user configuration: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
