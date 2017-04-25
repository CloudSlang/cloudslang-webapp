/******************************************************************************
 (c) Copyright 2017 Hewlett-Packard Development Company, L.P.
 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Apache License v2.0 which accompany this distribution.

 The Apache License is available at
 http://www.apache.org/licenses/LICENSE-2.0

 */
package io.cloudslang.web.controllers;

import io.cloudslang.web.client.FlowInputVo;
import io.cloudslang.web.client.FlowVo;
import io.cloudslang.web.client.VersionVo;
import io.cloudslang.web.services.FlowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Component
@RestController
@RequestMapping("cs/rest")
public class FlowController {

    private static String slangVersion;

    @Autowired
    private FlowServiceImpl flowServiceImpl;

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public VersionVo getVersions() {
        return new VersionVo(slangVersion);
    }

    @RequestMapping(value = "/flows/v1/{flowId}/inputs", method = RequestMethod.GET)
    public ResponseEntity<List<FlowInputVo>> getInputs(@PathVariable("flowId") String filepath) {

        try {
            List<FlowInputVo> inputsForFlow = flowServiceImpl.getInputsForFlow(filepath);
            return new ResponseEntity<>(inputsForFlow, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "flows/v1/flows", method = RequestMethod.GET)
    public ResponseEntity<List<FlowVo>> retrieveAvailableFlows() {

        try {
            List<FlowVo> allFlows = flowServiceImpl.getAllFlows();
            return new ResponseEntity<>(allFlows, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Value("${slang.version}")
    public void setVersion(String slangVersion) {
        FlowController.slangVersion = slangVersion;
    }
}
