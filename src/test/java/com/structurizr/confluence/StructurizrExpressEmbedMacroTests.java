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
    public void test_execute_ThrowsAnException_WhenAnExpressKeyIsNotSpecified() {
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            macro.execute(parameters, "", null);
        } catch (MacroExecutionException mee) {
            assertEquals(mee.getMessage(), "An Express key must be specified.");
        }

        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("expressKey", " ");
            macro.execute(parameters, "", null);
        } catch (MacroExecutionException mee) {
            assertEquals(mee.getMessage(), "An Express key must be specified.");
        }
    }

    @Test
    public void test_execute_ReturnsHtml_WhenBodyContentIsSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("expressKey", "d4d56ad3-2b23-4fd5-bea0-cf3879368f2e");

        String html = macro.execute(parameters, "A Structurizr Express definition", null);

        assertEquals(
                "<form id='structurizrEmbedForm_Express_1' target='structurizrEmbedIframe_Express1' method='post' action='https://structurizr.com/embed/express' style='display: none;'>\n" +
                        "<input name='key' value='d4d56ad3-2b23-4fd5-bea0-cf3879368f2e'/>\n" +
                        "<textarea name='definition'>A Structurizr Express definition</textarea>\n" +
                        "<input name='iframe' value='structurizrEmbedIframe_Express1' />\n" +
                        "<input name='src' value='' />\n" +
                        "</form>\n" +
                        "\n" +
                        "<iframe id='structurizrEmbedIframe_Express1' name='structurizrEmbedIframe_Express1' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                        "\n" +
                        "<script type='text/javascript'>\n" +
                        "document.getElementById('structurizrEmbedForm_Express_1').submit();\n" +
                        "</script>\n" +
                        "\n" +
                        "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

    @Test
    public void test_execute_ReturnsHtml_WhenASrcParameterIsSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("expressKey", "d4d56ad3-2b23-4fd5-bea0-cf3879368f2e");
        parameters.put("src", "https://gist.githubusercontent.com/simonbrowndotje/ee1c266dfd123a4d5660a8aa19da9e61/raw/21bed60ca5551f22f273aee10efedb1c57c7dd71/gistfile1.txt");

        String html = macro.execute(parameters, "A Structurizr Express definition", null);

        assertEquals(
                "<form id='structurizrEmbedForm_Express_1' target='structurizrEmbedIframe_Express1' method='post' action='https://structurizr.com/embed/express' style='display: none;'>\n" +
                "<input name='key' value='d4d56ad3-2b23-4fd5-bea0-cf3879368f2e'/>\n" +
                "<textarea name='definition'>A Structurizr Express definition</textarea>\n" +
                "<input name='iframe' value='structurizrEmbedIframe_Express1' />\n" +
                "<input name='src' value='https://gist.githubusercontent.com/simonbrowndotje/ee1c266dfd123a4d5660a8aa19da9e61/raw/21bed60ca5551f22f273aee10efedb1c57c7dd71/gistfile1.txt' />\n" +
                "</form>\n" +
                "\n" +
                "<iframe id='structurizrEmbedIframe_Express1' name='structurizrEmbedIframe_Express1' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript'>\n" +
                "document.getElementById('structurizrEmbedForm_Express_1').submit();\n" +
                "</script>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

}
