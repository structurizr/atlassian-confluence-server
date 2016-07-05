package com.structurizr.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

/**
 * A Confluence macro that embeds a single Structurizr Express diagram.
 */
public class StructurizrExpressEmbedMacro extends AbstractStructurizrMacro {

    static int COUNT = 1;

    private static final String TEMPLATE =
            "<form id='%s' target='%s' method='post' action='https://structurizr.com/embed/express' style='display: none;'>\n" +
            "<input name='key' value='%s'/>\n" +
            "<textarea name='definition'>%s</textarea>\n" +
            "<input name='iframe' value='%s' />\n" +
            "<input name='src' value='%s' />\n" +
            "</form>\n" +
            "\n" +
            "<iframe id='%s' name='%s' width='100%%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
            "\n" +
            "<script type='text/javascript'>\n" +
            "document.getElementById('%s').submit();\n" +
            "</script>\n" +
            "\n" +
            "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>";
    
    public String execute(Map<String, String> parameters, String bodyContent, ConversionContext conversionContext) throws MacroExecutionException {
        String formId = "structurizrEmbedForm_Express_" + COUNT;
        String iFrameId = "structurizrEmbedIframe_Express" + COUNT;
        COUNT++;

        String expressKey = parameters.get("expressKey");
        if (expressKey == null || expressKey.trim().length() == 0) {
            throw new MacroExecutionException("An Express key must be specified.");
        }

        String src = parameters.getOrDefault("src", "");

        return String.format(TEMPLATE, formId, iFrameId, expressKey, bodyContent, iFrameId, src, iFrameId, iFrameId, formId);
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.PLAIN_TEXT;
    }

}
