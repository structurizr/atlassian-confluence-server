package com.structurizr.confluence;

import com.atlassian.confluence.macro.MacroExecutionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StructurizrPrivateEmbedMacroTests {

    private StructurizrPrivateEmbedMacro macro = new StructurizrPrivateEmbedMacro();

    @Test
    public void test_execute_ThrowsAnException_WhenAWorkspaceIdIsNotSpecified() {
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            macro.execute(parameters, "", null);
            fail();
        } catch (MacroExecutionException mee) {
            assertEquals("A workspace ID must be specified.", mee.getMessage());
        }

        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("workspaceId", " ");
            macro.execute(parameters, "", null);
            fail();
        } catch (MacroExecutionException mee) {
            assertEquals("A workspace ID must be specified.", mee.getMessage());
        }
    }

    @Test
    public void test_execute_ThrowsAnException_WhenANonNumericWorkspaceIdIsSpecified() {
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("workspaceId", "NotANumber");
            macro.execute(parameters, "", null);
            fail();
        } catch (MacroExecutionException mee) {
            assertEquals("The workspace ID must be a number.", mee.getMessage());
        }
    }

    @Test
    public void test_execute_ThrowsAnException_WhenAnApiKeyIsNotSpecified() {
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("workspaceId", "1234");
            macro.execute(parameters, "", null);
            fail();
        } catch (MacroExecutionException mee) {
            assertEquals("An API key must be specified.", mee.getMessage());
        }
    }

    @Test
    public void test_execute_ReturnsHtml_WhenAllParametersAreSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("workspaceId", "1234");
        parameters.put("apiKey", "d4d56ad3-2b23-4fd5-bea0-cf3879368f2e");
        parameters.put("diagramKey", "Context");
        parameters.put("diagramSelector", "true");

        String html = macro.execute(parameters, "", null);

        assertEquals(
                "<form id='structurizrEmbedForm_1234_Context' target='structurizrEmbedIframe_1234_Context' method='post' action='https://structurizr.com/embed/1234' style='display:none;'>\n" +
                "<input name='apiKey' value='d4d56ad3-2b23-4fd5-bea0-cf3879368f2e'/>\n" +
                "<input name='diagram' value='Context' />\n" +
                "<input name='diagramSelector' value='true' />\n" +
                "<input name='iframe' value='structurizrEmbedIframe_1234_Context' />\n" +
                "</form>\n" +
                "\n" +
                "<iframe id='structurizrEmbedIframe_1234_Context' name='structurizrEmbedIframe_1234_Context' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript'>\n" +
                "    document.getElementById('structurizrEmbedForm_1234_Context').submit();\n" +
                "</script>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

    @Test
    public void test_execute_ReturnsHtmlWithADefaultDiagramKey_WhenADiagramKeyIsNotSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("workspaceId", "1234");
        parameters.put("apiKey", "d4d56ad3-2b23-4fd5-bea0-cf3879368f2e");
        parameters.put("diagramSelector", "true");

        String html = macro.execute(parameters, "", null);

        assertEquals(
                "<form id='structurizrEmbedForm_1234_1' target='structurizrEmbedIframe_1234_1' method='post' action='https://structurizr.com/embed/1234' style='display:none;'>\n" +
                "<input name='apiKey' value='d4d56ad3-2b23-4fd5-bea0-cf3879368f2e'/>\n" +
                "<input name='diagram' value='1' />\n" +
                "<input name='diagramSelector' value='true' />\n" +
                "<input name='iframe' value='structurizrEmbedIframe_1234_1' />\n" +
                "</form>\n" +
                "\n" +
                "<iframe id='structurizrEmbedIframe_1234_1' name='structurizrEmbedIframe_1234_1' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript'>\n" +
                "    document.getElementById('structurizrEmbedForm_1234_1').submit();\n" +
                "</script>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

    @Test
    public void test_execute_ReturnsHtmlWithADefaultDiagramSelector_WhenADiagramSelectorIsNotSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("workspaceId", "1234");
        parameters.put("apiKey", "d4d56ad3-2b23-4fd5-bea0-cf3879368f2e");
        parameters.put("diagramKey", "Context");

        String html = macro.execute(parameters, "", null);

        assertEquals(
                "<form id='structurizrEmbedForm_1234_Context' target='structurizrEmbedIframe_1234_Context' method='post' action='https://structurizr.com/embed/1234' style='display:none;'>\n" +
                "<input name='apiKey' value='d4d56ad3-2b23-4fd5-bea0-cf3879368f2e'/>\n" +
                "<input name='diagram' value='Context' />\n" +
                "<input name='diagramSelector' value='false' />\n" +
                "<input name='iframe' value='structurizrEmbedIframe_1234_Context' />\n" +
                "</form>\n" +
                "\n" +
                "<iframe id='structurizrEmbedIframe_1234_Context' name='structurizrEmbedIframe_1234_Context' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript'>\n" +
                "    document.getElementById('structurizrEmbedForm_1234_Context').submit();\n" +
                "</script>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

}