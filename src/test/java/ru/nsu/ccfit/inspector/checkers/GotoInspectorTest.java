package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlParser;
import ru.nsu.ccfit.inspector.inspectors.GotoInspector;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by artem on 16.12.16.
 */
public class GotoInspectorTest {

    @Test
    public void checkGoto() throws IOException {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";
        TsqlParser parser = TestUtils.getParser(query);
        ParseTree tree = TestUtils.getParserTree(parser);
        List<CodeSmell> smellList = TestUtils.getCodeSmells(new GotoInspector(parser, tree));
        assertEquals(0, smellList.size());
    }
}
