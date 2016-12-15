package ru.nsu.ccfit.inspector.checkers;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlLexer;
import ru.nsu.ccfit.inspector.TsqlParser;

import java.io.IOError;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 16.12.2016.
 */
public class SelectCheckerTest {

    @Test
    public void check () throws IOException{
        String query = "SELECT column_name,column_name " +
                        "FROM table_name;";

        ANTLRInputStream input = new ANTLRInputStream(new StringReader(query));

        TsqlLexer lexer = new ru.nsu.ccfit.inspector.TsqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TsqlParser parser = new ru.nsu.ccfit.inspector.TsqlParser(tokens);

        parser.setBuildParseTree(true);
        ParseTree tree = parser.tsql_file();

        SelectChecker checker = new SelectChecker(parser, tree);

        ArrayList<CodeSmell> codeSmells = new ArrayList<CodeSmell>();
        checker.check(codeSmells);
        System.out.println(codeSmells);
        //assertEquals(2, codeSmells.size());
    }
}
