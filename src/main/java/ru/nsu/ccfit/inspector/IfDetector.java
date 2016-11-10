package ru.nsu.ccfit.inspector;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.pattern.ParseTreeMatch;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marting422 on 10.11.16.
 */
public class IfDetector {

    public static void detect (String query, ArrayList codeSmells) throws IOException {

        ANTLRInputStream input = new ANTLRInputStream(new StringReader(query));
        TsqlLexer lexer = new TsqlLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TsqlParser parser = new TsqlParser(tokens);

        parser.setBuildParseTree(true);
        ParseTree tree = parser.tsql_file();


        String xpath = "//cfl_statement";
        String treePattern = "<IF> <search_condition> <sql_clause>";

        ParseTreePattern p =
                parser.compileParseTreePattern(treePattern, TsqlParser.RULE_cfl_statement);
        List<ParseTreeMatch> matches = p.findAll(tree, xpath);



        for (ParseTreeMatch match : matches) {
            codeSmells.add(new CodeSmell(0, "IF", match.getTree().getText()));
            //System.out.println(match.getTree().getText());
        }
    }
}
