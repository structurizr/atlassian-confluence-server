package com.structurizr.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

/**
 * A Confluence macro that embeds a single diagram from a public workspace.
 */
public class StructurizrPublicEmbedMacro extends AbstractStructurizrMacro {

    private static final String TEMPLATE =
            "<div style='max-width: %s'>\n" +
            "<iframe id='%s' src='https://structurizr.com/embed/%d?diagram=%s&diagramSelector=%s&iframe=%s' width='100%%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
            "</div>\n" +
            "\n" +
            "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>";

    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException {
        try {
            long workspaceId = getWorkspaceId(parameters);
            String diagramKey = getDiagramKey(parameters);
            String diagramSelector = getDiagramSelector(parameters);
            String iframeId = createIframeId(workspaceId, diagramKey);
            String width = getMaxWidth(parameters);

            return String.format(TEMPLATE, width, iframeId, workspaceId, diagramKey, diagramSelector, iframeId);
        } catch (NumberFormatException e) {
            throw new MacroExecutionException("The workspace ID must be a number.");
        }
    }

}
