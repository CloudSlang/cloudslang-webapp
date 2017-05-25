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

import io.cloudslang.lang.cli.utils.CompilerHelper;
import io.cloudslang.lang.compiler.SlangSource;
import io.cloudslang.lang.entities.CompilationArtifact;
import io.cloudslang.lang.entities.bindings.Input;
import io.cloudslang.web.client.FlowInputVo;
import io.cloudslang.web.client.FlowVo;
import io.cloudslang.web.helper.FileHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowServiceImpl implements FlowService {

    private static final String SLANG_FILE_PATTERN = "^.*\\.sl";

    private static final String FLOW_TAG = "flow:";

    private static final String WORKFLOW_TAG = "workflow:";

    @Autowired
    private CompilerHelper compilerHelper;

    public List<FlowVo> getAllFlows() throws IOException {
        File dir = new File(FileHelper.getContentPath());
        Collection<File> files = FileUtils
            .listFiles(dir, new RegexFileFilter(SLANG_FILE_PATTERN), DirectoryFileFilter.DIRECTORY);

        List<FlowVo> flows = new ArrayList<>();
        for (File file : files) {
            if (fileIsFlow(file)) {
                flows.add(new FlowVo(FileHelper.filePathToFlowName(file.getName()), FileHelper
                    .filePathToFlowId(file.getPath())));
            }
        }
        return flows;
    }

    private boolean fileIsFlow(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), SlangSource.getCloudSlangCharset());
        for (String line : lines) {
            if (line.contains(FLOW_TAG) && !line.contains(WORKFLOW_TAG)) {
                return true;
            }
        }
        return false;
    }

    public List<FlowInputVo> getInputsForFlow(String flowId) {
        CompilationArtifact compilationArtifact = compilerHelper.compile(FileHelper.flowIdToFilePath(flowId), Arrays
            .asList(FileHelper.getContentPath())); //TODO: check the dependencies

        if (compilationArtifact == null) {
            throw new IllegalArgumentException("Could not compile the given flow. Flow ID is invalid.");
        }

        List<Input> inputs = compilationArtifact.getInputs();
        List<FlowInputVo> inputsResult = new ArrayList<>();
        for (Input input : inputs) {
            if (!input.isPrivateInput()) {
                inputsResult.add(new FlowInputVo(input));
            }
        }
        return inputsResult;
    }
}