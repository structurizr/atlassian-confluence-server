package com.structurizr.confluence;

import com.atlassian.confluence.macro.MacroExecutionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StructurizrPublicEmbedMacroTests {

    private StructurizrPublicEmbedMacro macro = new StructurizrPublicEmbedMacro();

    @Test
    public void test_execute_ThrowsAnException_WhenAWorkspaceIdIsNotSpecified() {
        try {
            Map<String, String> parameters = new HashMap<>();
            macro.execute(parameters, "", null);
        } catch (MacroExecutionException mee) {
            assertEquals(mee.getMessage(), "A workspace ID must be specified.");
        }
    }

    @Test
    public void test_execute_ThrowsAnException_WhenANonNumericWorkspaceIdIsSpecified() {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("workspaceId", "NotANumber");
            macro.execute(parameters, "", null);
        } catch (MacroExecutionException mee) {
            assertEquals(mee.getMessage(), "The workspace ID must be a number.");
        }
    }

}
