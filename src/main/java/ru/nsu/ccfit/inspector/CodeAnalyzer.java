package ru.nsu.ccfit.inspector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.ccfit.inspector.inspectors.Inspector;
import ru.nsu.ccfit.inspector.inspectors.SelectWhereInspector;
import ru.nsu.ccfit.inspector.inspectors.StubInspector;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 10/20/2016.
 */
public class CodeAnalyzer {

    List<CodeSmell> codeSmells;

    public void printCodeSmells() {
        for (CodeSmell codeSmell : codeSmells) {
            codeSmell.print();
        }
    }

    public List<CodeSmell> analyze(String query) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(query));

        TsqlLexer lexer = new ru.nsu.ccfit.inspector.TsqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TsqlParser parser = new ru.nsu.ccfit.inspector.TsqlParser(tokens);

        parser.setBuildParseTree(true);
        ParseTree tree = parser.tsql_file();

        ArrayList<Inspector> inspectors = new ArrayList();
        inspectors.add(new StubInspector(parser, tree));
        inspectors.add(new SelectWhereInspector(parser, tree));

        codeSmells = new ArrayList<CodeSmell>();

        for (Inspector i : inspectors) {
            i.inspect(codeSmells);
        }

        return codeSmells;
    }
}
