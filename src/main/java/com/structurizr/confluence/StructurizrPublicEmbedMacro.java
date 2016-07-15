package com.structurizr.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

/**
 * A Confluence macro that embeds a single diagram from a public workspace.
 */
public class StructurizrPublicEmbedMacro extends AbstractStructurizrMacro {

    private static final String TEMPLATE =
            "<iframe id='%s' src='https://structurizr.com/embed/%d?diagram=%s&diagramSelector=%s&iframe=%s' width='100%%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
            "\n" +
            "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>";

    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException {
        try {
            String workspaceIdAsString = parameters.get("workspaceId");
            if (workspaceIdAsString == null || workspaceIdAsString.trim().length() == 0) {
                throw new MacroExecutionException("A workspace ID must be specified.");
            }

            long workspaceId = Long.parseLong(workspaceIdAsString);
            String diagramKey = "1";
            if (parameters.containsKey("diagramKey")) {
                diagramKey = parameters.get("diagramKey");
            }

            String diagramSelector = "false";
            if (parameters.containsKey("diagramSelector")) {
                diagramSelector = parameters.get("diagramSelector");
            }

            String iframeId = createIframeId(workspaceId, diagramKey);

            return String.format(TEMPLATE, iframeId, workspaceId, diagramKey, diagramSelector, iframeId);
        } catch (NumberFormatException e) {
            throw new MacroExecutionException("The workspace ID must be a number.");
        }
    }

}
