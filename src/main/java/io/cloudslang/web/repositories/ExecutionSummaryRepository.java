/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web.repositories;

import io.cloudslang.web.entities.ExecutionSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: kravtsov
 * Date: 3/2/15
 * Time: 2:51 PM
 */

@Repository
public interface ExecutionSummaryRepository extends JpaRepository<ExecutionSummaryEntity, Long> {

    ExecutionSummaryEntity findByExecutionId(Long executionId);

}