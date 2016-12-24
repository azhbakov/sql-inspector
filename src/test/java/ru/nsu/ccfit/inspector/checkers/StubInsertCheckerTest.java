package ru.nsu.ccfit.inspector.checkers;


import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlParser;


import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by marting422 on 10.11.16.
 */
public class StubInsertCheckerTest {

    @Test
    public void checkInsert() throws IOException {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";
        TsqlParser parser = TestUtils.getParser(query);
        ParseTree tree = TestUtils.getParserTree(parser);
        StubInsertChecker checker = new StubInsertChecker(parser,tree);
        List<CodeSmell> smellList = TestUtils.getCodeSmells(checker);
        assertEquals(2, smellList.size());
    }
}