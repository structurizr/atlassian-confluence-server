package com.structurizr.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

/**
 * A Confluence macro that embeds a single diagram from a Structurizr workspace.
 */
public class StructurizrWorkspaceEmbedMacro extends AbstractStructurizrMacro {

    private static final String TEMPLATE =
            "<form id=\"%s\" target=\"%s\" method=\"post\" action=\"%s/embed/%d\" style=\"display:none;\">\n" +
            "<input name=\"apiKey\" value=\"%s\"/>\n" +
            "<input name=\"diagram\" value=\"%s\" />\n" +
            "<input name=\"diagramSelector\" value=\"%s\" />\n" +
            "<input name=\"iframe\" value=\"%s\" />\n" +
            "</form>\n" +
            "\n" +
            "<iframe id=\"%s\" name=\"%s\" width=\"100%%\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" allowfullscreen=\"true\"></iframe>\n" +
            "\n" +
            "<script type=\"text/javascript\">\n" +
            "    document.getElementById(\"%s\").submit();\n" +
            "</script>\n" +
            "\n" +
            "<script type=\"text/javascript\" src=\"%s/static/js/structurizr-responsive-embed.js\"></script>";

    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException {
        try {
            String structurizrUrl = getStructurizrUrl(parameters);
            long workspaceId = getWorkspaceId(parameters);
            String diagramKey = getDiagramKey(parameters);
            String diagramSelector = getDiagramSelector(parameters);
            String iframeId = createIframeId(workspaceId, diagramKey);
            String apiKey = parameters.get("apiKey");
            String formId = createFormId(workspaceId, diagramKey);

            return String.format(TEMPLATE, formId, iframeId, structurizrUrl, workspaceId, apiKey, diagramKey, diagramSelector, iframeId, iframeId, iframeId, formId, structurizrUrl);
        } catch (NumberFormatException e) {
            throw new MacroExecutionException("The workspace ID must be a number.");
        }
    }

    protected String createFormId(long workspaceId, String diagramKey) {
        return String.format("structurizrEmbedForm_%d_%s", workspaceId, diagramKey);
    }

}
