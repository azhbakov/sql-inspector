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
 * Created by Martin on 16.12.2016.
 */


public class SelectCheckerTest {

    @Test
    public void check() throws IOException {
        String query = "SELECT column_name,column_name " +
                "FROM table_name;";


        TsqlParser parser = TestUtils.getParser(query);
        ParseTree tree = TestUtils.getParserTree(parser);
        List<CodeSmell> codeSmells = TestUtils.getCodeSmells(new SelectChecker(parser, tree));
        System.out.println(codeSmells);
        assertEquals(1, codeSmells.size());
    }
}
