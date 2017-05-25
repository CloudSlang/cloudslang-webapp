/*******************************************************************************
 * (c) Copyright 2017 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.web.client;

public class FlowVo {

    private String id;

    private String name;

    private boolean runnable;

    public FlowVo(String name, String id) {
        this.name = name;
        this.id = id;
        this.runnable = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isRunnable() {
        return runnable;
    }

}
