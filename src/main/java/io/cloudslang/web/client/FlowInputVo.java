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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.cloudslang.lang.entities.bindings.Input;

public class FlowInputVo {

    private String name;

    private boolean required;

    private boolean sensitive;

    @JsonProperty("default")
    private String defaultValue;

    public FlowInputVo(Input input) {
        this.name = input.getName();
        this.required = input.isRequired();
        this.sensitive = input.isSensitive();
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isSensitive() {
        return sensitive;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
