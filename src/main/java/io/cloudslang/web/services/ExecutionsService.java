/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web.services;

import io.cloudslang.score.facade.execution.ExecutionStatus;
import io.cloudslang.web.client.ExecutionSummaryWebVo;
import io.cloudslang.web.client.ExecutionTriggeringVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: kravtsov
 * Date: 3/8/15
 * Time: 3:11 PM
 */

@Service
public interface ExecutionsService {

    /**
     * Trigger flow written in slang
     *
     * @param executionTriggeringVo the value object containing the necessary information to run the flow
     * @return the execution ID in score
     */
    @Transactional
    Long triggerExecution(ExecutionTriggeringVo executionTriggeringVo);

    @Transactional(readOnly = true)
    ExecutionSummaryWebVo getExecution(Long executionId);

    @Transactional
    void updateExecution(Long executionId, ExecutionStatus status, String result, String outputs);
}
