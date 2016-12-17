package ru.nsu.ccfit.inspector.checkers;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.CodeSmell;
import ru.nsu.ccfit.inspector.TsqlLexer;
import ru.nsu.ccfit.inspector.TsqlParser;
import ru.nsu.ccfit.inspector.inspectors.Inspector;
import ru.nsu.ccfit.inspector.inspectors.StubInspector;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by artem on 16.12.16.
 */
class TestUtils {

    public static TsqlParser getParser(String query) throws IOException
    {
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(query));
        TsqlLexer lexer = new TsqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new TsqlParser(tokens);
    }
    public static ParseTree getParserTree(TsqlParser parser) throws IOException
    {
        parser.setBuildParseTree(true);
        return parser.tsql_file();
    }
    public static List<CodeSmell> getCodeSmells(Inspector inspector)
    {
        ArrayList<CodeSmell> codeSmells = new ArrayList<CodeSmell>();
        inspector.inspect(codeSmells);
        return codeSmells;
    }
    public static List<CodeSmell> getCodeSmells(Checker checker)
    {
        ArrayList<CodeSmell> codeSmells = new ArrayList<CodeSmell>();
        checker.check(codeSmells);
        return codeSmells;
    }
}
