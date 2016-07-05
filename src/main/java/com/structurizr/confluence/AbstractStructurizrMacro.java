package com.structurizr.confluence;

import com.atlassian.confluence.macro.Macro;

public abstract class AbstractStructurizrMacro implements Macro {

    protected String createIframeId(long workspaceId, String diagramKey) {
        return String.format("structurizrEmbedIframe_%d_%s", workspaceId, diagramKey);
    }

    @Override
    public Macro.BodyType getBodyType() {
        return Macro.BodyType.NONE;
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }

}
