/*******************************************************************************
 * (c) Copyright 2017 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web.services;

import io.cloudslang.web.client.FlowInputVo;
import io.cloudslang.web.client.FlowVo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface FlowService {


    /**
     * List all flows available for execution
     *
     * @return List containing information about all the available flows
     */
    List<FlowVo> getAllFlows() throws IOException;


    /**
     * Retrieve the list of the inputs for the flow
     *
     * @param flowId id of the flow the inputs are being returned
     * @return List of inputs for the flow
     */
    List<FlowInputVo> getInputsForFlow(String flowId);

}
