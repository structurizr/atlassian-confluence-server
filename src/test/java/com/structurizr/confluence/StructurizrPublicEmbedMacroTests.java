package com.structurizr.confluence;

import com.atlassian.confluence.macro.MacroExecutionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StructurizrPublicEmbedMacroTests {

    private StructurizrPublicEmbedMacro macro = new StructurizrPublicEmbedMacro();

    @Test
    public void test_execute_ThrowsAnException_WhenAWorkspaceIdIsNotSpecified() {
        try {
            Map<String, String> parameters = new HashMap<String, String>();
            macro.execute(parameters, "", null);
            fail();
        } catch (MacroExecutionException mee) {
            assertEquals(mee.getMessage(), "A workspace ID must be specified.");
        }

        try {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("workspaceId", " ");
            macro.execute(parameters, "", null);
            fail();
        } catch (MacroExecutionException mee) {
            assertEquals(mee.getMessage(), "A workspace ID must be specified.");
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
    public void test_execute_ReturnsHtml_WhenAllParametersAreSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("structurizrUrl", "https://structurizr.com");
        parameters.put("workspaceId", "1234");
        parameters.put("diagramKey", "Context");
        parameters.put("diagramSelector", "true");

        String html = macro.execute(parameters, "", null);

        assertEquals(
                "<iframe id='structurizrEmbedIframe_1234_Context' src='https://structurizr.com/embed/1234?diagram=Context&diagramSelector=true&iframe=structurizrEmbedIframe_1234_Context' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

    @Test
    public void test_execute_ReturnsHtmlWithADefaultDiagramKey_WhenADiagramKeyIsNotSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("structurizrUrl", "https://structurizr.com");
        parameters.put("workspaceId", "1234");
        parameters.put("diagramSelector", "true");

        String html = macro.execute(parameters, "", null);

        assertEquals(
                "<iframe id='structurizrEmbedIframe_1234_1' src='https://structurizr.com/embed/1234?diagram=1&diagramSelector=true&iframe=structurizrEmbedIframe_1234_1' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

    @Test
    public void test_execute_ReturnsHtmlWithADefaultDiagramSelector_WhenADiagramSelectorIsNotSpecified() throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("structurizrUrl", "https://structurizr.com");
        parameters.put("workspaceId", "1234");
        parameters.put("diagramKey", "Context");

        String html = macro.execute(parameters, "", null);

        assertEquals(
                "<iframe id='structurizrEmbedIframe_1234_Context' src='https://structurizr.com/embed/1234?diagram=Context&diagramSelector=false&iframe=structurizrEmbedIframe_1234_Context' width='100%' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' allowfullscreen='true'></iframe>\n" +
                "\n" +
                "<script type='text/javascript' src='https://structurizr.com/static/js/structurizr-responsive-embed.js'></script>", html);
    }

}
