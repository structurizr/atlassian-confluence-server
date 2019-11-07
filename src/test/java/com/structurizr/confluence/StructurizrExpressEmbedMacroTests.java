package com.structurizr.confluence;

import com.atlassian.confluence.macro.MacroExecutionException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StructurizrExpressEmbedMacroTests {

    private StructurizrExpressEmbedMacro macro = new StructurizrExpressEmbedMacro();

    @Before
    public void setUp() {
        StructurizrExpressEmbedMacro.COUNT = 1; // todo ... :-)
    }

    @Test
    public void test_execute_ReturnsHtml_WhenBodyContentIsSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("structurizrUrl", "https://structurizr.com");

        String html = macro.execute(parameters, "A Structurizr Express definition", null);

        assertEquals(
                "<form id=\"structurizrEmbedForm_Express_1\" target=\"structurizrEmbedIframe_Express1\" method=\"post\" action=\"https://structurizr.com/embed/express\" style=\"display: none;\">\n" +
                        "<textarea name=\"definition\">A Structurizr Express definition</textarea>\n" +
                        "<input name=\"iframe\" value=\"structurizrEmbedIframe_Express1\" />\n" +
                        "</form>\n" +
                        "\n" +
                        "<iframe id=\"structurizrEmbedIframe_Express1\" name=\"structurizrEmbedIframe_Express1\" width=\"100%\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" allowfullscreen=\"true\"></iframe>\n" +
                        "\n" +
                        "<script type=\"text/javascript\">\n" +
                        "document.getElementById(\"structurizrEmbedForm_Express_1\").submit();\n" +
                        "</script>\n" +
                        "\n" +
                        "<script type=\"text/javascript\" src=\"https://structurizr.com/static/js/structurizr-responsive-embed.js\"></script>", html);
    }

}