/*******************************************************************************
 * (c) Copyright 2017 Hewlett-Packard Development Company, L.P.
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Import(SlangSpringConfiguration.class)
@ImportResource("spring/slangWebappContext.xml")
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "io.cloudslang")
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
