# cloudslang-webapp

[![Join the chat at https://gitter.im/CloudSlang/cloudslang-webapp](https://badges.gitter.im/CloudSlang/cloudslang-webapp.svg)](https://gitter.im/CloudSlang/cloudslang-webapp?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This repository contains a Spring Boot REST API based webapp for CloudSlang.
It gives one the ability to:
 *  List CloudSlang workflows, 
 *  Get input details of a particular workflow, 
 *  Trigger CloudSlang workflows,
 *  Get execution details.

1. [Description](#description)
2. [General Usage](#general-usage)
3. [Contribution Guideline](contribution-guideline)

<a name="description"/>

## Description

The webapp is at 0.0.1 version.
It uses CloudSlang 1.0.6 and Spring Boot 1.2.8.

<a name="general-usage"/>

## General usage

In order to build the webapp and run it

#### PREREQUISITES

> Java 8 installed in order to run.

> [Maven 3.3.9](https://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/) installed in order to build. 

1. Clone this repository and build it, by running (**mvn clean install**).
   Go to the target folder and look for cloudslang-webapp-0.0.1.zip

2. Extract the .zip file to a folder of your choice.
   
   ```
   Example of extracted folder structure:
       bin\
       configuration\
       logs\
       maven\
       security\
       cloudslang-webapp-0.0.1.jar
   ```
   
3. If you are on Windows go to the bin\win64\ folder and trigger the webapp.bat
4. If you are on Linux trigger the webapp by using the following command while making sure that $APP_HOME points to
   the folder where you extracted the zip:
   
   ```
    java -jar -Dapp.home="$APP_HOME" "$APP_HOME\cloudslang-webapp-0.0.1.jar"
   ```
> The webapp will start by default on port 8080 and the following REST API calls 
can be performed:
 
 ```
    GET: http://<FQDN>:8080/cs/rest/version
    GET: http://<FQDN>:8080/cs/rest/flows/v1/flows
    GET: http://<FQDN>:8080/cs/rest/flows/v1/{flowId}/inputs
    POST: http://<FQDN>:8080/cs/rest/v1/executions
    GET: http://<FQDN>:8080/cs/rest/v1/executions/{executionId}
    
    where:
        a. {flowId} = CloudSlang based UUID of the flow (namespace.name)
        b. {executionId} = The ID of the execution retrieved by the POST executions call
        c. The POST on executions expects a body similar to:
        
            {    
                "slangFlowId": "io.cloudslang.base.examples.parallel_loop.create_directory",
                "runInputs": {
                "directory_name" : "test_folder"
                },
                "systemProperties": {}
            }
 
 ```   
 **Note:** In the configuration folder there is a _cslang.properties_ file where among other things 
 one can specify the filesystem path of the CloudSlang workflows.
 
    Simply change the content.path to a desired value like below and start the webapp: 
 
    content.path=C://cloud-slang-content/content/
5. In the _cloudslang-webapp/src/main/resources/security/users.yml_ file users with READ/EXECUTE rights
  can be configured upon desire as authentication is enabled by default: 
  ```application:
       users:
         - username: admin
           password: admin
           roles: READ, EXECUTE
         - username: author
           password: author
           roles: READ
  ```
<a name="contribution-guideline"/>                                       
                                       
## Contribution Guideline
                                       
Read our Contribution Guide [here](CONTRIBUTING.md).   