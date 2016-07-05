package com.structurizr.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

/**
 * A Confluence macro that embeds a single diagram from a private workspace.
 */
public class StructurizrPrivateEmbedMacro extends AbstractStructurizrMacro {

    private static final String TEMPLATE =
            "<form id='%s' target='%s' method='post' action='https://structurizr.com/embed/%d' style='display:none;'>\n" +
            "<input name='apiKey' value='%s'/>\n" +
            "<input name='diagram' value='%s' />\n" +
            "<input name='diagramSelector' value='%s' />\n" +
            "<input name='iframe' value='%s' />\n" +
            "</form>\n" +
            "\n" +
            "<iframe id='%s' name='%s' width='100%%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
            "\n" +
            "<script type='text/javascript'>\n" +
            "    document.getElementById('%s').submit();\n" +
            "</script>\n" +
            "\n" +
            "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>";

    @Override
    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException {
        try {
            long workspaceId = Long.parseLong(parameters.get("workspaceId"));
            String apiKey = parameters.get("apiKey");
            String diagramKey = parameters.getOrDefault("diagramKey", "1");
            String diagramSelector = parameters.getOrDefault("diagramSelector", "false");

            String formId = createFormId(workspaceId, diagramKey);
            String iframeId = createIframeId(workspaceId, diagramKey);

            return String.format(TEMPLATE, formId, iframeId, workspaceId, apiKey, diagramKey, diagramSelector, iframeId, iframeId, iframeId, formId);
        } catch (NumberFormatException e) {
            throw new MacroExecutionException("Workspace ID must be a number.");
        }
    }

    protected String createFormId(long workspaceId, String diagramKey) {
        return String.format("structurizrEmbedForm_%d_%s", workspaceId, diagramKey);
    }

}
