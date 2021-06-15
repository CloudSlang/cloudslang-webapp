package io.cloudslang.web.services;

import io.cloudslang.web.security.ApplicationUser;
import io.cloudslang.web.security.ApplicationUsers;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Map;
import java.util.Iterator;

public class CredentialServicesImpl implements CredentialServices {

    private static final String USER_CREDENTIALS_FILEPATH;

    ApplicationUser applicationUser ;
    List<ApplicationUser> userList = new ArrayList<>();
    ApplicationUsers applicationUsers = new ApplicationUsers();

    @Override
    public void loadUserCredentials() throws IOException {
        String appHome = System.getProperty("app.home");
        String propertyFilePath = appHome + File.separator + USER_CREDENTIALS_FILEPATH;
        File propertyFile = new File(propertyFilePath);
        Properties rawProperties = new Properties();

        InputStream inputStream = new FileInputStream(new File(propertyFilePath));

        Yaml yaml = new Yaml();
        Map<String, Object> userData = yaml.load(inputStream);

        List<Map<String, String>> userMapList = (List<Map<String, String>>) ((Map<String, Object>)userData.get("application")).get("users");
        Iterator itr = userMapList.iterator();

        while ( itr.hasNext() ) {
            Map<String, String> userMap = (Map<String, String>)(itr.next());
            applicationUser = new ApplicationUser();
            applicationUser.setUsername(userMap.get("username"));
            applicationUser.setPassword(userMap.get("password"));
            applicationUser.setRoles((String[]) ((userMap.get("roles")).split(",")));

            userList.add(applicationUser);
        }

        applicationUsers.setUsers(userList);

    }



    static {
        USER_CREDENTIALS_FILEPATH = "security" + File.separator + "users.yml";
    }
}



