package ru.nsu.ccfit.inspector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 10/20/2016.
 */
public class CodeAnalyzer {

    private CodeAnalyzer()
    {

    }
    /*
        @param args - cmd arguments
    */
    public static void main(String[] args) throws IOException {
        String query = "create procedure Audit_MassInsert as declare @RetVal int " +
                "IF a>b begin insert abc (a,b,c)values ('a','b','c') end " +
                "return @RetVal " +
                "IF a>b insert abc (a,b,c)values ('a','b','c')";

        List<CodeSmell> codeSmells = analyze(query);

        for (CodeSmell codeSmell : codeSmells) {
            codeSmell.print();
        }
    }

    /*
    * @param query - input string
    *
    * @return List<CodeSmell>
    * */
    public static List<CodeSmell> analyze(String query) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(new StringReader(query));

        TsqlLexer lexer = new ru.nsu.ccfit.inspector.TsqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TsqlParser parser = new ru.nsu.ccfit.inspector.TsqlParser(tokens);

        parser.setBuildParseTree(true);
        ParseTree tree = parser.tsql_file();

        InspectorFactory factory = new InspectorFactoryImpl(parser, tree);

        List<CodeSmell> codeSmells = new ArrayList<>();

        factory.createStubInspector().inspect(codeSmells);

        return codeSmells;
    }
}
