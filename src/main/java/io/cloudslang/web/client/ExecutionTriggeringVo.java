/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web.client;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kravtsov
 * Date: 3/1/15
 * Time: 11:26 AM
 */
public class ExecutionTriggeringVo {

    private String slangFlowId;
    private Map<String, Object> runInputs;
    private Map<String, String> systemProperties;


    public String getSlangFlowId() {
        return slangFlowId;
    }

    public void setSlangFlowId(String slangFlowId) {
        this.slangFlowId = slangFlowId;
    }

    public Map<String, Object> getRunInputs() {
        return runInputs;
    }

    public void setRunInputs(Map<String, Object> runInputs) {
        this.runInputs = runInputs;
    }

    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
    }
}
