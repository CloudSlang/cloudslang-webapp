/*******************************************************************************
 * (c) Copyright 2017 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import javax.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties
public class ApplicationUsers {

    private final List<ApplicationUser> users = new ArrayList<>();

    public List<ApplicationUser> getUsers() {
        return this.users;
    }


    @PostConstruct
    public void loadUserCredentials() throws IOException {
        String appHome = System.getProperty("app.home");
        String propertyFilePath = appHome + File.separator + "security" + File.separator + "users.yml";
        File propertyFile = new File(propertyFilePath);
        Properties rawProperties = new Properties();

        InputStream inputStream = new FileInputStream(new File(propertyFilePath));

        Yaml yaml = new Yaml();
        Map<String, Object> userData = yaml.load(inputStream);

        List<Map<String, String>> userMapList = (List<Map<String, String>>) ((Map<String, Object>)userData.get("application")).get("users");
        Iterator itr = userMapList.iterator();

        while ( itr.hasNext() ) {
            Map<String, String> userMap = (Map<String, String>)(itr.next());
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setUsername(userMap.get("username"));
            applicationUser.setPassword(userMap.get("password"));
            applicationUser.setRoles((String[]) ((userMap.get("roles")).split(",")));

            users.add(applicationUser);
        }
    }
}
