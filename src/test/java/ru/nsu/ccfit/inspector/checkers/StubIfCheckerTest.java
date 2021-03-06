package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlLexer;
import ru.nsu.ccfit.inspector.TsqlParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by marting422 on 10.11.16.
 */
public class StubIfCheckerTest {
    @Test
    public void checkIf() throws IOException {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";
        TsqlParser parser = TestUtils.getParser(query);
        ParseTree tree = TestUtils.getParserTree(parser);
        List<CodeSmell> smellList = TestUtils.getCodeSmells(new StubIfChecker(parser, tree));
        assertEquals(2, smellList.size());
    }
}